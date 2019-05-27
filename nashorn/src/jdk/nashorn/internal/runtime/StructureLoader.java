package jdk.nashorn.internal.runtime;

import static jdk.nashorn.internal.codegen.Compiler.SCRIPTS_PACKAGE;
import static jdk.nashorn.internal.codegen.Compiler.binaryName;
import static jdk.nashorn.internal.codegen.CompilerConstants.JS_OBJECT_PREFIX;

import java.security.ProtectionDomain;
import jdk.nashorn.internal.codegen.ObjectClassGenerator;

/**
 * Responsible for on the fly construction of structure classes.
 */
final class StructureLoader extends NashornLoader {
    private static final String JS_OBJECT_PREFIX_EXTERNAL = binaryName(SCRIPTS_PACKAGE) + '.' + JS_OBJECT_PREFIX.symbolName();

    /**
     * Constructor.
     */
    StructureLoader(final ClassLoader parent) {
        super(parent);
    }

    static boolean isStructureClass(final String name) {
        return name.startsWith(JS_OBJECT_PREFIX_EXTERNAL);
    }

    @Override
    protected Class<?> findClass(final String name) throws ClassNotFoundException {
        if (isStructureClass(name)) {
            return generateClass(name, name.substring(JS_OBJECT_PREFIX_EXTERNAL.length()));
        }
        return super.findClass(name);
    }

    /**
     * Generate a layout class.
     * @param name       Name of class.
     * @param descriptor Layout descriptor.
     * @return Generated class.
     */
    private Class<?> generateClass(final String name, final String descriptor) {
        final Context context = Context.getContextTrusted();

        final byte[] code = new ObjectClassGenerator(context).generate(descriptor);
        return defineClass(name, code, 0, code.length, new ProtectionDomain(null, getPermissions(null)));
    }
}
