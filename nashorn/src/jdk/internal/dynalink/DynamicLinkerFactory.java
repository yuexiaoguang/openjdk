package jdk.internal.dynalink;

import java.lang.invoke.MutableCallSite;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import jdk.internal.dynalink.beans.BeansLinker;
import jdk.internal.dynalink.linker.GuardingDynamicLinker;
import jdk.internal.dynalink.linker.GuardingTypeConverterFactory;
import jdk.internal.dynalink.linker.LinkRequest;
import jdk.internal.dynalink.support.AutoDiscovery;
import jdk.internal.dynalink.support.BottomGuardingDynamicLinker;
import jdk.internal.dynalink.support.ClassLoaderGetterContextProvider;
import jdk.internal.dynalink.support.CompositeGuardingDynamicLinker;
import jdk.internal.dynalink.support.CompositeTypeBasedGuardingDynamicLinker;
import jdk.internal.dynalink.support.LinkerServicesImpl;
import jdk.internal.dynalink.support.TypeConverterFactory;

/**
 * A factory class for creating {@link DynamicLinker}s. The most usual dynamic linker is a linker that is a composition
 * of all {@link GuardingDynamicLinker}s known and pre-created by the caller as well as any
 * {@link AutoDiscovery automatically discovered} guarding linkers and the standard fallback {@link BeansLinker}. See
 * {@link DynamicLinker} documentation for tips on how to use this class.
 */
public class DynamicLinkerFactory {

    /**
     * Default value for {@link #setUnstableRelinkThreshold(int) unstable relink threshold}.
     */
    public static final int DEFAULT_UNSTABLE_RELINK_THRESHOLD = 8;

    private boolean classLoaderExplicitlySet = false;
    private ClassLoader classLoader;

    private List<? extends GuardingDynamicLinker> prioritizedLinkers;
    private List<? extends GuardingDynamicLinker> fallbackLinkers;
    private int runtimeContextArgCount = 0;
    private boolean syncOnRelink = false;
    private int unstableRelinkThreshold = DEFAULT_UNSTABLE_RELINK_THRESHOLD;

    /**
     * Sets the class loader for automatic discovery of available linkers. If not set explicitly, then the thread
     * context class loader at the time of {@link #createLinker()} invocation will be used.
     *
     * @param classLoader the class loader used for the autodiscovery of available linkers.
     */
    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
        classLoaderExplicitlySet = true;
    }

    /**
     * Sets the prioritized linkers. Language runtimes using this framework will usually precreate at least the linker
     * for their own language. These linkers will be consulted first in the resulting dynamic linker, before any
     * autodiscovered linkers. If the framework also autodiscovers a linker of the same class as one of the prioritized
     * linkers, it will be ignored and the explicit prioritized instance will be used.
     *
     * @param prioritizedLinkers the list of prioritized linkers. Null can be passed to indicate no prioritized linkers
     * (this is also the default value).
     */
    public void setPrioritizedLinkers(List<? extends GuardingDynamicLinker> prioritizedLinkers) {
        this.prioritizedLinkers =
                prioritizedLinkers == null ? null : new ArrayList<>(prioritizedLinkers);
    }

    /**
     * Sets the prioritized linkers. Language runtimes using this framework will usually precreate at least the linker
     * for their own language. These linkers will be consulted first in the resulting dynamic linker, before any
     * autodiscovered linkers. If the framework also autodiscovers a linker of the same class as one of the prioritized
     * linkers, it will be ignored and the explicit prioritized instance will be used.
     *
     * @param prioritizedLinkers a list of prioritized linkers.
     */
    public void setPrioritizedLinkers(GuardingDynamicLinker... prioritizedLinkers) {
        setPrioritizedLinkers(Arrays.asList(prioritizedLinkers));
    }

    /**
     * Sets a single prioritized linker. Identical to calling {@link #setPrioritizedLinkers(List)} with a single-element
     * list.
     *
     * @param prioritizedLinker the single prioritized linker. Must not be null.
     * @throws IllegalArgumentException if null is passed.
     */
    public void setPrioritizedLinker(GuardingDynamicLinker prioritizedLinker) {
        if(prioritizedLinker == null) {
            throw new IllegalArgumentException("prioritizedLinker == null");
        }
        this.prioritizedLinkers = Collections.singletonList(prioritizedLinker);
    }

    /**
     * Sets the fallback linkers. These linkers will be consulted last in the resulting composite linker, after any
     * autodiscovered linkers. If the framework also autodiscovers a linker of the same class as one of the fallback
     * linkers, it will be ignored and the explicit fallback instance will be used.
     *
     * @param fallbackLinkers the list of fallback linkers. Can be empty to indicate the caller wishes to set no
     * fallback linkers.
     */
    public void setFallbackLinkers(List<? extends GuardingDynamicLinker> fallbackLinkers) {
        this.fallbackLinkers = fallbackLinkers == null ? null : new ArrayList<>(fallbackLinkers);
    }

    /**
     * Sets the fallback linkers. These linkers will be consulted last in the resulting composite linker, after any
     * autodiscovered linkers. If the framework also autodiscovers a linker of the same class as one of the fallback
     * linkers, it will be ignored and the explicit fallback instance will be used.
     *
     * @param fallbackLinkers the list of fallback linkers. Can be empty to indicate the caller wishes to set no
     * fallback linkers. If it is left as null, the standard fallback {@link BeansLinker} will be used.
     */
    public void setFallbackLinkers(GuardingDynamicLinker... fallbackLinkers) {
        setFallbackLinkers(Arrays.asList(fallbackLinkers));
    }

    /**
     * Sets the number of arguments in the call sites that represent the stack context of the language runtime creating
     * the linker. If the language runtime uses no context information passed on stack, then it should be zero
     * (the default value). If it is set to nonzero value, then every dynamic call site emitted by this runtime must
     * have the argument list of the form: {@code (this, contextArg1[, contextArg2[, ...]], normalArgs)}. It is
     * advisable to only pass one context-specific argument, though, of an easily recognizable, runtime specific type
     * encapsulating the runtime thread local state.
     *
     * @param runtimeContextArgCount the number of language runtime context arguments in call sites.
     */
    public void setRuntimeContextArgCount(int runtimeContextArgCount) {
        if(runtimeContextArgCount < 0) {
            throw new IllegalArgumentException("runtimeContextArgCount < 0");
        }
        this.runtimeContextArgCount = runtimeContextArgCount;
    }

    /**
     * Sets whether the linker created by this factory will invoke {@link MutableCallSite#syncAll(MutableCallSite[])}
     * after a call site is relinked. Defaults to false. You probably want to set it to true if your runtime supports
     * multithreaded execution of dynamically linked code.
     * @param syncOnRelink true for invoking sync on relink, false otherwise.
     */
    public void setSyncOnRelink(boolean syncOnRelink) {
        this.syncOnRelink = syncOnRelink;
    }

    /**
     * Sets the unstable relink threshold; the number of times a call site is relinked after which it will be
     * considered unstable, and subsequent link requests for it will indicate this.
     * @param unstableRelinkThreshold the new threshold. Must not be less than zero. The value of zero means that
     * call sites will never be considered unstable.
     * @see LinkRequest#isCallSiteUnstable()
     */
    public void setUnstableRelinkThreshold(int unstableRelinkThreshold) {
        if(unstableRelinkThreshold < 0) {
            throw new IllegalArgumentException("unstableRelinkThreshold < 0");
        }
        this.unstableRelinkThreshold = unstableRelinkThreshold;
    }

    /**
     * Creates a new dynamic linker consisting of all the prioritized, autodiscovered, and fallback linkers.
     *
     * @return the new dynamic Linker
     */
    public DynamicLinker createLinker() {
        // Treat nulls appropriately
        if(prioritizedLinkers == null) {
            prioritizedLinkers = Collections.emptyList();
        }
        if(fallbackLinkers == null) {
            fallbackLinkers = Collections.singletonList(new BeansLinker());
        }

        // Gather classes of all precreated (prioritized and fallback) linkers.
        // We'll filter out any discovered linkers of the same class.
        final Set<Class<? extends GuardingDynamicLinker>> knownLinkerClasses =
                new HashSet<>();
        addClasses(knownLinkerClasses, prioritizedLinkers);
        addClasses(knownLinkerClasses, fallbackLinkers);

        final ClassLoader effectiveClassLoader = classLoaderExplicitlySet ? classLoader : getThreadContextClassLoader();
        final List<GuardingDynamicLinker> discovered = AutoDiscovery.loadLinkers(effectiveClassLoader);
        // Now, concatenate ...
        final List<GuardingDynamicLinker> linkers =
                new ArrayList<>(prioritizedLinkers.size() + discovered.size()
                        + fallbackLinkers.size());
        // ... prioritized linkers, ...
        linkers.addAll(prioritizedLinkers);
        // ... filtered discovered linkers, ...
        for(GuardingDynamicLinker linker: discovered) {
            if(!knownLinkerClasses.contains(linker.getClass())) {
                linkers.add(linker);
            }
        }
        // ... and finally fallback linkers.
        linkers.addAll(fallbackLinkers);
        final List<GuardingDynamicLinker> optimized = CompositeTypeBasedGuardingDynamicLinker.optimize(linkers);
        final GuardingDynamicLinker composite;
        switch(linkers.size()) {
            case 0: {
                composite = BottomGuardingDynamicLinker.INSTANCE;
                break;
            }
            case 1: {
                composite = optimized.get(0);
                break;
            }
            default: {
                composite = new CompositeGuardingDynamicLinker(optimized);
                break;
            }
        }

        final List<GuardingTypeConverterFactory> typeConverters = new LinkedList<>();
        for(GuardingDynamicLinker linker: linkers) {
            if(linker instanceof GuardingTypeConverterFactory) {
                typeConverters.add((GuardingTypeConverterFactory)linker);
            }
        }

        return new DynamicLinker(new LinkerServicesImpl(new TypeConverterFactory(typeConverters), composite),
                runtimeContextArgCount, syncOnRelink, unstableRelinkThreshold);
    }

    private static ClassLoader getThreadContextClassLoader() {
        return AccessController.doPrivileged(new PrivilegedAction<ClassLoader>() {
            @Override
            public ClassLoader run() {
                return Thread.currentThread().getContextClassLoader();
            }
        }, ClassLoaderGetterContextProvider.GET_CLASS_LOADER_CONTEXT);
    }

    private static void addClasses(Set<Class<? extends GuardingDynamicLinker>> knownLinkerClasses,
            List<? extends GuardingDynamicLinker> linkers) {
        for(GuardingDynamicLinker linker: linkers) {
            knownLinkerClasses.add(linker.getClass());
        }
    }
}
