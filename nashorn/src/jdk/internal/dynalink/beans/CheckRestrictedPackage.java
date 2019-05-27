package jdk.internal.dynalink.beans;

import java.lang.reflect.Modifier;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.Permissions;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;

/**
 * A utility class to check whether a given class is in a package with restricted access e.g. "sun.*" etc.
 */
class CheckRestrictedPackage {
    private static final AccessControlContext NO_PERMISSIONS_CONTEXT = createNoPermissionsContext();

    /**
     * Returns true if the class is either not public, or it resides in a package with restricted access.
     * @param clazz the class to test
     * @return true if the class is either not public, or it resides in a package with restricted access.
     */
    static boolean isRestrictedClass(Class<?> clazz) {
        if(!Modifier.isPublic(clazz.getModifiers())) {
            // Non-public classes are always restricted
            return true;
        }
        final SecurityManager sm = System.getSecurityManager();
        if(sm == null) {
            // No further restrictions if we don't have a security manager
            return false;
        }
        final String name = clazz.getName();
        final int i = name.lastIndexOf('.');
        if (i == -1) {
            // Classes in default package are never restricted
            return false;
        }
        // Do a package access check from within an access control context with no permissions
        try {
            AccessController.doPrivileged(new PrivilegedAction<Void>() {
                @Override
                public Void run() {
                    sm.checkPackageAccess(name.substring(0, i));
                    return null;
                }
            }, NO_PERMISSIONS_CONTEXT);
        } catch(SecurityException e) {
            return true;
        }
        return false;
    }

    private static AccessControlContext createNoPermissionsContext() {
        return new AccessControlContext(new ProtectionDomain[] { new ProtectionDomain(null, new Permissions()) });
    }
}
