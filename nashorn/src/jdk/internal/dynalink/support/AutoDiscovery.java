package jdk.internal.dynalink.support;

import java.util.LinkedList;
import java.util.List;
import java.util.ServiceLoader;
import jdk.internal.dynalink.DynamicLinkerFactory;
import jdk.internal.dynalink.linker.GuardingDynamicLinker;

/**
 * Provides methods for automatic discovery of all guarding dynamic linkers listed in the
 * <tt>/META-INF/services/jdk.internal.dynalink.linker.GuardingDynamicLinker</tt> resources of all JAR files for a
 * particular class loader. Ordinarily, you will not use this class directly, but you will use a
 * {@link DynamicLinkerFactory} instead.
 */
public class AutoDiscovery {

    private AutoDiscovery() {
    }

    /**
     * Discovers all guarding dynamic linkers listed in JAR files of the context class loader of the current thread.
     *
     * @return a list of available linkers. Can be zero-length list but not null.
     */
    public static List<GuardingDynamicLinker> loadLinkers() {
        return getLinkers(ServiceLoader.load(GuardingDynamicLinker.class));
    }

    /**
     * Discovers all guarding dynamic linkers listed in JAR files of the specified class loader.
     *
     * @param cl the class loader to use
     * @return a list of guarding dynamic linkers available through the specified class loader. Can be zero-length list
     * but not null.
     */
    public static List<GuardingDynamicLinker> loadLinkers(ClassLoader cl) {
        return getLinkers(ServiceLoader.load(GuardingDynamicLinker.class, cl));
    }

    /**
     * I can't believe there's no Collections API for making a List given an Iterator...
     */
    private static <T> List<T> getLinkers(ServiceLoader<T> loader) {
        final List<T> list = new LinkedList<>();
        for(final T linker: loader) {
            list.add(linker);
        }
        return list;
    }
}
