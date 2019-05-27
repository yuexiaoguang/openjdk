package jdk.nashorn.internal.codegen.types;

import static jdk.internal.org.objectweb.asm.Opcodes.BIPUSH;
import static jdk.internal.org.objectweb.asm.Opcodes.I2D;
import static jdk.internal.org.objectweb.asm.Opcodes.I2L;
import static jdk.internal.org.objectweb.asm.Opcodes.IADD;
import static jdk.internal.org.objectweb.asm.Opcodes.IAND;
import static jdk.internal.org.objectweb.asm.Opcodes.ICONST_0;
import static jdk.internal.org.objectweb.asm.Opcodes.ICONST_1;
import static jdk.internal.org.objectweb.asm.Opcodes.ICONST_2;
import static jdk.internal.org.objectweb.asm.Opcodes.ICONST_3;
import static jdk.internal.org.objectweb.asm.Opcodes.ICONST_4;
import static jdk.internal.org.objectweb.asm.Opcodes.ICONST_5;
import static jdk.internal.org.objectweb.asm.Opcodes.ICONST_M1;
import static jdk.internal.org.objectweb.asm.Opcodes.IDIV;
import static jdk.internal.org.objectweb.asm.Opcodes.ILOAD;
import static jdk.internal.org.objectweb.asm.Opcodes.IMUL;
import static jdk.internal.org.objectweb.asm.Opcodes.INEG;
import static jdk.internal.org.objectweb.asm.Opcodes.IOR;
import static jdk.internal.org.objectweb.asm.Opcodes.IREM;
import static jdk.internal.org.objectweb.asm.Opcodes.IRETURN;
import static jdk.internal.org.objectweb.asm.Opcodes.ISHL;
import static jdk.internal.org.objectweb.asm.Opcodes.ISHR;
import static jdk.internal.org.objectweb.asm.Opcodes.ISTORE;
import static jdk.internal.org.objectweb.asm.Opcodes.ISUB;
import static jdk.internal.org.objectweb.asm.Opcodes.IUSHR;
import static jdk.internal.org.objectweb.asm.Opcodes.IXOR;
import static jdk.internal.org.objectweb.asm.Opcodes.SIPUSH;
import static jdk.nashorn.internal.codegen.CompilerConstants.staticCallNoLookup;

import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.nashorn.internal.codegen.CompilerConstants;
import jdk.nashorn.internal.codegen.ObjectClassGenerator;

/**
 * Type class: INT
 */
class IntType extends BitwiseType {

    private static final CompilerConstants.Call TO_STRING = staticCallNoLookup(Integer.class, "toString", String.class, int.class);
    private static final CompilerConstants.Call VALUE_OF  = staticCallNoLookup(Integer.class, "valueOf", Integer.class, int.class);

    protected IntType() {
        super("int", int.class, 2, 1);
    }

    @Override
    public Type nextWider() {
        return LONG;
    }

    @Override
    public Class<?> getBoxedType() {
        return Integer.class;
    }

    @Override
    public Type ldc(final MethodVisitor method, final Object c) {
        assert c instanceof Integer;

        final int value = ((Integer) c).intValue();

        switch (value) {
            case -1:
                method.visitInsn(ICONST_M1);
                break;
            case 0:
                method.visitInsn(ICONST_0);
                break;
            case 1:
                method.visitInsn(ICONST_1);
                break;
            case 2:
                method.visitInsn(ICONST_2);
                break;
            case 3:
                method.visitInsn(ICONST_3);
                break;
            case 4:
                method.visitInsn(ICONST_4);
                break;
            case 5:
                method.visitInsn(ICONST_5);
                break;
            default:
                if (value == (byte) value) {
                    method.visitIntInsn(BIPUSH, value);
                } else if (value == (short) value) {
                    method.visitIntInsn(SIPUSH, value);
                } else {
                    method.visitLdcInsn(c);
                }
                break;
        }

        return Type.INT;
    }

    @Override
    public Type convert(final MethodVisitor method, final Type to) {
        if (to.isEquivalentTo(this)) {
            return to;
        }

        if (to.isNumber()) {
            method.visitInsn(I2D);
        } else if (to.isLong()) {
            method.visitInsn(I2L);
        } else if (to.isBoolean()) {
            //nop
        } else if (to.isString()) {
            invokeStatic(method, TO_STRING);
        } else if (to.isObject()) {
            invokeStatic(method, VALUE_OF);
        } else {
            assert false : "Illegal conversion " + this + " -> " + to;
        }

        return to;
    }

    @Override
    public Type add(final MethodVisitor method) {
        method.visitInsn(IADD);
        return INT;
    }

    @Override
    public Type shr(final MethodVisitor method) {
        method.visitInsn(IUSHR);
        return INT;
    }

    @Override
    public Type sar(final MethodVisitor method) {
        method.visitInsn(ISHR);
        return INT;
    }

    @Override
    public Type shl(final MethodVisitor method) {
        method.visitInsn(ISHL);
        return INT;
    }

    @Override
    public Type and(final MethodVisitor method) {
        method.visitInsn(IAND);
        return INT;
    }

    @Override
    public Type or(final MethodVisitor method) {
        method.visitInsn(IOR);
        return INT;
    }

    @Override
    public Type xor(final MethodVisitor method) {
        method.visitInsn(IXOR);
        return INT;
    }

    @Override
    public Type load(final MethodVisitor method, final int slot) {
        assert slot != -1;
        method.visitVarInsn(ILOAD, slot);
        return INT;
    }

    @Override
    public void store(final MethodVisitor method, final int slot) {
        assert slot != -1;
        method.visitVarInsn(ISTORE, slot);
    }

    @Override
    public Type sub(final MethodVisitor method) {
        method.visitInsn(ISUB);
        return INT;
    }

    @Override
    public Type mul(final MethodVisitor method) {
        method.visitInsn(IMUL);
        return INT;
    }

    @Override
    public Type div(final MethodVisitor method) {
        method.visitInsn(IDIV);
        return INT;
    }

    @Override
    public Type rem(final MethodVisitor method) {
        method.visitInsn(IREM);
        return INT;
    }

    @Override
    public Type neg(final MethodVisitor method) {
        method.visitInsn(INEG);
        return INT;
    }

    @Override
    public void _return(final MethodVisitor method) {
        method.visitInsn(IRETURN);
    }

    @Override
    public Type loadUndefined(final MethodVisitor method) {
        method.visitLdcInsn(ObjectClassGenerator.UNDEFINED_INT);
        return INT;
    }

    @Override
    public Type cmp(final MethodVisitor method, final boolean isCmpG) {
        assert false : "unsupported operation";
        return null;
    }

    @Override
    public Type cmp(final MethodVisitor method) {
        assert false : "unsupported operation";
        return null;
    }
}
