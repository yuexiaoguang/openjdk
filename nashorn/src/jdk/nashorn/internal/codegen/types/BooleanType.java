package jdk.nashorn.internal.codegen.types;

import static jdk.internal.org.objectweb.asm.Opcodes.ICONST_0;
import static jdk.internal.org.objectweb.asm.Opcodes.ICONST_1;
import static jdk.internal.org.objectweb.asm.Opcodes.ILOAD;
import static jdk.internal.org.objectweb.asm.Opcodes.IRETURN;
import static jdk.internal.org.objectweb.asm.Opcodes.ISTORE;
import static jdk.nashorn.internal.codegen.CompilerConstants.staticCallNoLookup;

import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.nashorn.internal.codegen.CompilerConstants;
import jdk.nashorn.internal.codegen.ObjectClassGenerator;
import jdk.nashorn.internal.runtime.JSType;

/**
 * The boolean type class
 */
public final class BooleanType extends Type {

    private static final CompilerConstants.Call VALUE_OF = staticCallNoLookup(Boolean.class, "valueOf", Boolean.class, boolean.class);

    /**
     * Constructor
     */
    protected BooleanType() {
        super("boolean", boolean.class, 1, 1);
    }

    @Override
    public Type nextWider() {
        return INT;
    }

    @Override
    public Class<?> getBoxedType() {
        return Boolean.class;
    }

    @Override
    public Type loadUndefined(final MethodVisitor method) {
        method.visitLdcInsn(ObjectClassGenerator.UNDEFINED_INT);
        return BOOLEAN;
    }

    @Override
    public void _return(final MethodVisitor method) {
        method.visitInsn(IRETURN);
    }

    @Override
    public Type load(final MethodVisitor method, final int slot) {
        assert slot != -1;
        method.visitVarInsn(ILOAD, slot);
        return BOOLEAN;
    }

    @Override
    public void store(final MethodVisitor method, final int slot) {
        assert slot != -1;
        method.visitVarInsn(ISTORE, slot);
    }

    @Override
    public Type ldc(final MethodVisitor method, final Object c) {
        assert c instanceof Boolean;
        method.visitInsn((Boolean) c ? ICONST_1 : ICONST_0);
        return BOOLEAN;
    }

    @Override
    public Type convert(final MethodVisitor method, final Type to) {
        if (isEquivalentTo(to)) {
            return to;
        }

        if (to.isNumber()) {
            convert(method, OBJECT);
            invokeStatic(method, JSType.TO_NUMBER);
        } else if (to.isInteger()) {
            return to; // do nothing.
        } else if (to.isLong()) {
            convert(method, OBJECT);
            invokeStatic(method, JSType.TO_UINT32);
        } else if (to.isLong()) {
            convert(method, OBJECT);
            invokeStatic(method, JSType.TO_LONG);
        } else if (to.isString()) {
            invokeStatic(method, VALUE_OF);
            invokeStatic(method, JSType.TO_PRIMITIVE_TO_STRING);
        } else if (to.isObject()) {
            invokeStatic(method, VALUE_OF);
        } else {
            assert false : "Illegal conversion " + this + " -> " + to;
        }

        return to;
    }

    @Override
    public Type add(final MethodVisitor method) {
        assert false : "unsupported operation";
        return null;
    }
}
