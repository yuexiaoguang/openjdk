package com.sun.tools.internal.xjc;

/**
 * Class defined for safe calls of getClassLoader methods of any kind (context/system/class
 * classloader. This MUST be package private and defined in every package which
 * uses such invocations.
 */
class SecureLoader {

    static ClassLoader getContextClassLoader() {
        if (System.getSecurityManager() == null) {
            return Thread.currentThread().getContextClassLoader();
        } else {
            return java.security.AccessController.doPrivileged(
                    new java.security.PrivilegedAction<ClassLoader>() {
                        public ClassLoader run() {
                            return Thread.currentThread().getContextClassLoader();
                        }
                    });
        }
    }

    static ClassLoader getClassClassLoader(final Class c) {
        if (System.getSecurityManager() == null) {
            return c.getClassLoader();
        } else {
            return java.security.AccessController.doPrivileged(
                    new java.security.PrivilegedAction<ClassLoader>() {
                        public ClassLoader run() {
                            return c.getClassLoader();
                        }
                    });
        }
    }

    static ClassLoader getSystemClassLoader() {
        if (System.getSecurityManager() == null) {
            return ClassLoader.getSystemClassLoader();
        } else {
            return java.security.AccessController.doPrivileged(
                    new java.security.PrivilegedAction<ClassLoader>() {
                        public ClassLoader run() {
                            return ClassLoader.getSystemClassLoader();
                        }
                    });
        }
    }

    static void setContextClassLoader(final ClassLoader cl) {
        if (System.getSecurityManager() == null) {
            Thread.currentThread().setContextClassLoader(cl);
        } else {
            java.security.AccessController.doPrivileged(
                    new java.security.PrivilegedAction<ClassLoader>() {
                        public ClassLoader run() {
                            Thread.currentThread().setContextClassLoader(cl);
                            return null;
                        }
                    });
        }
    }

    static ClassLoader getParentClassLoader(final ClassLoader cl) {
        if (System.getSecurityManager() == null) {
            return cl.getParent();
        } else {
            return java.security.AccessController.doPrivileged(
                    new java.security.PrivilegedAction<ClassLoader>() {
                        public ClassLoader run() {
                            return cl.getParent();
                        }
                    });
        }
    }


}
