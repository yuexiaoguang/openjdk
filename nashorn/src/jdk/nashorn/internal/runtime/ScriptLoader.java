package jdk.nashorn.internal.runtime;

import java.security.CodeSource;
import java.security.ProtectionDomain;

/**
 * Responsible for loading script generated classes.
 */
final class ScriptLoader extends NashornLoader {
    private static final String NASHORN_PKG_PREFIX = "jdk.nashorn.internal.";

    private final Context context;

    /*package-private*/ Context getContext() {
        return context;
    }

    /**
     * Constructor.
     */
    ScriptLoader(final ClassLoader parent, final Context context) {
        super(parent);
        this.context = context;
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        checkPackageAccess(name);
        if (name.startsWith(NASHORN_PKG_PREFIX)) {
            return context.getSharedLoader().loadClass(name);
        }
        return super.loadClass(name, resolve);
    }

    // package-private and private stuff below this point

    /**
     * Install a class for use by the Nashorn runtime
     *
     * @param name Binary name of class.
     * @param data Class data bytes.
     * @param cs CodeSource code source of the class bytes.
     *
     * @return Installed class.
     */
    synchronized Class<?> installClass(final String name, final byte[] data, final CodeSource cs) {
        if (cs == null) {
            return defineClass(name, data, 0, data.length, new ProtectionDomain(null, getPermissions(null)));
        }
        return defineClass(name, data, 0, data.length, cs);
    }
}
