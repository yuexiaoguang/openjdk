package jdk.internal.dynalink.support;

import java.security.AccessControlContext;
import java.security.Permissions;
import java.security.ProtectionDomain;

/**
 * This class exposes a canonical {@link AccessControlContext} with a single {@link RuntimePermission} for
 * {@code "getClassLoader"} permission that is used by other parts of the code to narrow their set of permissions when
 * they're retrieving class loaders in privileged blocks.
 */
public class ClassLoaderGetterContextProvider {
    /**
     * Canonical instance of {@link AccessControlContext} with a single {@link RuntimePermission} for
     * {@code "getClassLoader"} permission.
     */
    public static final AccessControlContext GET_CLASS_LOADER_CONTEXT;
    static {
        final Permissions perms = new Permissions();
        perms.add(new RuntimePermission("getClassLoader"));
        GET_CLASS_LOADER_CONTEXT = new AccessControlContext(
                new ProtectionDomain[] { new ProtectionDomain(null, perms) });
    }
}
