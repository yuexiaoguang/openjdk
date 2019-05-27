package jdk.internal.dynalink;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.util.List;
import jdk.internal.dynalink.linker.GuardedInvocation;
import jdk.internal.dynalink.linker.GuardingDynamicLinker;
import jdk.internal.dynalink.linker.LinkRequest;
import jdk.internal.dynalink.linker.LinkerServices;
import jdk.internal.dynalink.support.CallSiteDescriptorFactory;
import jdk.internal.dynalink.support.LinkRequestImpl;
import jdk.internal.dynalink.support.Lookup;
import jdk.internal.dynalink.support.RuntimeContextLinkRequestImpl;

/**
 * The linker for {@link RelinkableCallSite} objects. Users of it (scripting frameworks and language runtimes) have to
 * create a linker using the {@link DynamicLinkerFactory} and invoke its link method from the invokedynamic bootstrap
 * methods to set the target of all the call sites in the code they generate. Usual usage would be to create one class
 * per language runtime to contain one linker instance as:
 *
 * <pre>
 * class MyLanguageRuntime {
 *     private static final GuardingDynamicLinker myLanguageLinker = new MyLanguageLinker();
 *     private static final DynamicLinker dynamicLinker = createDynamicLinker();
 *
 *     private static DynamicLinker createDynamicLinker() {
 *         final DynamicLinkerFactory factory = new DynamicLinkerFactory();
 *         factory.setPrioritizedLinker(myLanguageLinker);
 *         return factory.createLinker();
 *     }
 *
 *     public static CallSite bootstrap(MethodHandles.Lookup caller, String name, MethodType type) {
 *         return dynamicLinker.link(new MonomorphicCallSite(CallSiteDescriptorFactory.create(lookup, name, type)));
 *     }
 * }
 * </pre>
 *
 * Note how there are three components you will need to provide here:
 * <ul>
 * <li>You're expected to provide a {@link GuardingDynamicLinker} for your own language. If your runtime doesn't
 * have its own language and/or object model (i.e. it's a generic scripting shell), you don't need to implement a
 * dynamic linker; you would simply not invoke the {@code setPrioritizedLinker} method on the factory, or even better,
 * simply use {@link DefaultBootstrapper}.</li>
 * <li>The performance of the programs can depend on your choice of the class to represent call sites. The above
 * example used {@link MonomorphicCallSite}, but you might want to use {@link ChainedCallSite} instead. You'll need to
 * experiment and decide what fits your language runtime the best. You can subclass either of these or roll your own if
 * you need to.</li>
 * <li>You also need to provide {@link CallSiteDescriptor}s to your call sites. They are immutable objects that contain
 * all the information about the call site: the class performing the lookups, the name of the method being invoked, and
 * the method signature. The library has a default {@link CallSiteDescriptorFactory} for descriptors that you can use,
 * or you can create your own descriptor classes, especially if you need to add further information (values passed in
 * additional parameters to the bootstrap method) to them.</li>
 * </ul>
 */
public class DynamicLinker {

    private static final String CLASS_NAME = DynamicLinker.class.getName();
    private static final String RELINK_METHOD_NAME = "relink";

    private static final String INITIAL_LINK_CLASS_NAME = "java.lang.invoke.MethodHandleNatives";
    private static final String INITIAL_LINK_METHOD_NAME = "linkCallSite";

    private final LinkerServices linkerServices;
    private final int runtimeContextArgCount;
    private final boolean syncOnRelink;
    private final int unstableRelinkThreshold;

    /**
     * Creates a new dynamic linker.
     *
     * @param linkerServices the linkerServices used by the linker, created by the factory.
     * @param runtimeContextArgCount see {@link DynamicLinkerFactory#setRuntimeContextArgCount(int)}
     */
    DynamicLinker(LinkerServices linkerServices, int runtimeContextArgCount, boolean syncOnRelink,
            int unstableRelinkThreshold) {
        if(runtimeContextArgCount < 0) {
            throw new IllegalArgumentException("runtimeContextArgCount < 0");
        }
        if(unstableRelinkThreshold < 0) {
            throw new IllegalArgumentException("unstableRelinkThreshold < 0");
        }
        this.runtimeContextArgCount = runtimeContextArgCount;
        this.linkerServices = linkerServices;
        this.syncOnRelink = syncOnRelink;
        this.unstableRelinkThreshold = unstableRelinkThreshold;
    }

    /**
     * Links an invokedynamic call site. It will install a method handle into the call site that invokes the relinking
     * mechanism of this linker. Next time the call site is invoked, it will be linked for the actual arguments it was
     * invoked with.
     *
     * @param callSite the call site to link.
     * @return the callSite, for easy call chaining.
     */
    public <T extends RelinkableCallSite> T link(final T callSite) {
        callSite.initialize(createRelinkAndInvokeMethod(callSite, 0));
        return callSite;
    }

    /**
     * Returns the object representing the lower level linker services of this class that are normally exposed to
     * individual language-specific linkers. While as a user of this class you normally only care about the
     * {@link #link(RelinkableCallSite)} method, in certain circumstances you might want to use the lower level services
     * directly; either to lookup specific method handles, to access the type converters, and so on.
     * @return the object representing the linker services of this class.
     */
    public LinkerServices getLinkerServices() {
        return linkerServices;
    }

    private static final MethodHandle RELINK = Lookup.findOwnSpecial(MethodHandles.lookup(), RELINK_METHOD_NAME,
            MethodHandle.class, RelinkableCallSite.class, int.class, Object[].class);

    private MethodHandle createRelinkAndInvokeMethod(final RelinkableCallSite callSite, int relinkCount) {
        // Make a bound MH of invoke() for this linker and call site
        final MethodHandle boundRelinker = MethodHandles.insertArguments(RELINK, 0, this, callSite, Integer.valueOf(
                relinkCount));
        // Make a MH that gathers all arguments to the invocation into an Object[]
        final MethodType type = callSite.getDescriptor().getMethodType();
        final MethodHandle collectingRelinker = boundRelinker.asCollector(Object[].class, type.parameterCount());
        return MethodHandles.foldArguments(MethodHandles.exactInvoker(type), collectingRelinker.asType(
                type.changeReturnType(MethodHandle.class)));
    }

    /**
     * Relinks a call site conforming to the invocation arguments.
     *
     * @param callSite the call site itself
     * @param arguments arguments to the invocation
     * @return return the method handle for the invocation
     * @throws Exception rethrows any exception thrown by the linkers
     */
    @SuppressWarnings("unused")
    private MethodHandle relink(RelinkableCallSite callSite, int relinkCount, Object... arguments) throws Exception {
        final CallSiteDescriptor callSiteDescriptor = callSite.getDescriptor();
        final boolean unstableDetectionEnabled = unstableRelinkThreshold > 0;
        final boolean callSiteUnstable = unstableDetectionEnabled && relinkCount >= unstableRelinkThreshold;
        final LinkRequest linkRequest =
                runtimeContextArgCount == 0 ? new LinkRequestImpl(callSiteDescriptor, callSiteUnstable, arguments)
                        : new RuntimeContextLinkRequestImpl(callSiteDescriptor, callSiteUnstable, arguments,
                                runtimeContextArgCount);

        // Find a suitable method handle with a guard
        GuardedInvocation guardedInvocation = linkerServices.getGuardedInvocation(linkRequest);

        // None found - throw an exception
        if(guardedInvocation == null) {
            throw new NoSuchDynamicMethodException(callSiteDescriptor.toString());
        }

        // If our call sites have a runtime context, and the linker produced a context-stripped invocation, adapt the
        // produced invocation into contextual invocation (by dropping the context...)
        if(runtimeContextArgCount > 0) {
            final MethodType origType = callSiteDescriptor.getMethodType();
            final MethodHandle invocation = guardedInvocation.getInvocation();
            if(invocation.type().parameterCount() == origType.parameterCount() - runtimeContextArgCount) {
                final List<Class<?>> prefix = origType.parameterList().subList(1, runtimeContextArgCount + 1);
                final MethodHandle guard = guardedInvocation.getGuard();
                guardedInvocation = guardedInvocation.dropArguments(1, prefix);
            }
        }

        int newRelinkCount = relinkCount;
        // Note that the short-circuited "&&" evaluation below ensures we'll increment the relinkCount until
        // threshold + 1 but not beyond that. Threshold + 1 is treated as a special value to signal that resetAndRelink
        // has already executed once for the unstable call site; we only want the call site to throw away its current
        // linkage once, when it transitions to unstable.
        if(unstableDetectionEnabled && newRelinkCount <= unstableRelinkThreshold && newRelinkCount++ == unstableRelinkThreshold) {
            callSite.resetAndRelink(guardedInvocation, createRelinkAndInvokeMethod(callSite, newRelinkCount));
        } else {
            callSite.relink(guardedInvocation, createRelinkAndInvokeMethod(callSite, newRelinkCount));
        }
        if(syncOnRelink) {
            MutableCallSite.syncAll(new MutableCallSite[] { (MutableCallSite)callSite });
        }
        return guardedInvocation.getInvocation();
    }

    /**
     * Returns a stack trace element describing the location of the call site currently being linked on the current
     * thread. The operation internally creates a Throwable object and inspects its stack trace, so it's potentially
     * expensive. The recommended usage for it is in writing diagnostics code.
     * @return a stack trace element describing the location of the call site currently being linked, or null if it is
     * not invoked while a call site is being linked.
     */
    public static StackTraceElement getLinkedCallSiteLocation() {
        final StackTraceElement[] trace = new Throwable().getStackTrace();
        for(int i = 0; i < trace.length - 1; ++i) {
            final StackTraceElement frame = trace[i];
            if(isRelinkFrame(frame) || isInitialLinkFrame(frame)) {
                return trace[i + 1];
            }
        }
        return null;
    }

    /**
     * Deprecated because of not precise name.
     * @deprecated Use {@link #getLinkedCallSiteLocation()} instead.
     * @return see non-deprecated method
     */
    @Deprecated
    public static StackTraceElement getRelinkedCallSiteLocation() {
        return getLinkedCallSiteLocation();
    }

    /**
     * Returns true if the frame represents {@code MethodHandleNatives.linkCallSite()}, the frame immediately on top of
     * the call site frame when the call site is being linked for the first time.
     * @param frame the frame
     * @return true if this frame represents {@code MethodHandleNatives.linkCallSite()}
     */
    private static boolean isInitialLinkFrame(final StackTraceElement frame) {
        return testFrame(frame, INITIAL_LINK_METHOD_NAME, INITIAL_LINK_CLASS_NAME);
    }

    /**
     * Returns true if the frame represents {@code DynamicLinker.relink()}, the frame immediately on top of the call
     * site frame when the call site is being relinked (linked for second and subsequent times).
     * @param frame the frame
     * @return true if this frame represents {@code DynamicLinker.relink()}
     */
    private static boolean isRelinkFrame(final StackTraceElement frame) {
        return testFrame(frame, RELINK_METHOD_NAME, CLASS_NAME);
    }

    private static boolean testFrame(final StackTraceElement frame, final String methodName, final String className) {
        return methodName.equals(frame.getMethodName()) && className.equals(frame.getClassName());
    }
}