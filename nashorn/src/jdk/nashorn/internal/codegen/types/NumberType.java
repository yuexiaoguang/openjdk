package jdk.nashorn.internal.codegen.types;

import static jdk.internal.org.objectweb.asm.Opcodes.DADD;
import static jdk.internal.org.objectweb.asm.Opcodes.DCMPG;
import static jdk.internal.org.objectweb.asm.Opcodes.DCMPL;
import static jdk.internal.org.objectweb.asm.Opcodes.DCONST_0;
import static jdk.internal.org.objectweb.asm.Opcodes.DCONST_1;
import static jdk.internal.org.objectweb.asm.Opcodes.DDIV;
import static jdk.internal.org.objectweb.asm.Opcodes.DLOAD;
import static jdk.internal.org.objectweb.asm.Opcodes.DMUL;
import static jdk.internal.org.objectweb.asm.Opcodes.DNEG;
import static jdk.internal.org.objectweb.asm.Opcodes.DREM;
import static jdk.internal.org.objectweb.asm.Opcodes.DRETURN;
import static jdk.internal.org.objectweb.asm.Opcodes.DSTORE;
import static jdk.internal.org.objectweb.asm.Opcodes.DSUB;
import static jdk.nashorn.internal.codegen.CompilerConstants.staticCallNoLookup;

import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.nashorn.internal.codegen.CompilerConstants;
import jdk.nashorn.internal.codegen.ObjectClassGenerator;
import jdk.nashorn.internal.runtime.JSType;

class NumberType extends NumericType {

    private static final CompilerConstants.Call VALUE_OF = staticCallNoLookup(Double.class, "valueOf", Double.class, double.class);

    protected NumberType() {
        super("double", double.class, 4, 2);
    }

    @Override
    public Type nextWider() {
        return OBJECT;
    }

    @Override
    public Class<?> getBoxedType() {
        return Double.class;
    }

    @Override
    public Type cmp(final MethodVisitor method, final boolean isCmpG) {
        method.visitInsn(isCmpG ? DCMPG : DCMPL);
        return INT;
    }

    @Override
    public Type load(final MethodVisitor method, final int slot) {
        assert slot != -1;
        method.visitVarInsn(DLOAD, slot);
        return NUMBER;
    }

    @Override
    public void store(final MethodVisitor method, final int slot) {
        assert slot != -1;
        method.visitVarInsn(DSTORE, slot);
    }

    @Override
    public Type loadUndefined(final MethodVisitor method) {
        method.visitLdcInsn(ObjectClassGenerator.UNDEFINED_DOUBLE);
        return NUMBER;
    }

    @Override
    public Type ldc(final MethodVisitor method, final Object c) {
        assert c instanceof Double;

        final double value = (Double) c;

        if (Double.doubleToLongBits(value) == 0L) { // guard against -0.0
            method.visitInsn(DCONST_0);
        } else if (value == 1.0) {
            method.visitInsn(DCONST_1);
        } else {
            method.visitLdcInsn(value);
        }

        return NUMBER;
    }

    @Override
    public Type convert(final MethodVisitor method, final Type to) {
        if (isEquivalentTo(to)) {
            return null;
        }

        if (to.isInteger()) {
            invokeStatic(method, JSType.TO_INT32_D);
        } else if (to.isLong()) {
            invokeStatic(method, JSType.TO_INT64_D);
        } else if (to.isBoolean()) {
            invokeStatic(method, JSType.TO_BOOLEAN_D);
        } else if (to.isString()) {
            invokeStatic(method, JSType.TO_STRING_D);
        } else if (to.isObject()) {
            invokeStatic(method, VALUE_OF);
        } else {
            assert false : "Illegal conversion " + this + " -> " + to;
        }

        return to;
    }

    @Override
    public Type add(final MethodVisitor method) {
        method.visitInsn(DADD);
        return NUMBER;
    }

    @Override
    public Type sub(final MethodVisitor method) {
        method.visitInsn(DSUB);
        return NUMBER;
    }

    @Override
    public Type mul(final MethodVisitor method) {
        method.visitInsn(DMUL);
        return NUMBER;
    }

    @Override
    public Type div(final MethodVisitor method) {
        method.visitInsn(DDIV);
        return NUMBER;
    }

    @Override
    public Type rem(final MethodVisitor method) {
        method.visitInsn(DREM);
        return NUMBER;
    }

    @Override
    public Type neg(final MethodVisitor method) {
        method.visitInsn(DNEG);
        return NUMBER;
    }

    @Override
    public void _return(final MethodVisitor method) {
        method.visitInsn(DRETURN);
    }
}
