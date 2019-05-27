package jdk.nashorn.internal.codegen.types;

import jdk.internal.org.objectweb.asm.MethodVisitor;

/**
 * Numeric operations, not supported by all types
 */
interface BytecodeNumericOps {

    /**
     * Pop and negate the value on top of the stack and push the result
     *
     * @param method method visitor
     *
     * @return result type
     */
    Type neg(MethodVisitor method);

    /**
     * Pop two values on top of the stack and subtract the first from the
     * second, pushing the result on the stack
     *
     * @param method method visitor
     *
     * @return result type
     */
    Type sub(MethodVisitor method);

    /**
     * Pop and multiply the two values on top of the stack and push the result
     * on the stack
     *
     * @param method method visitor
     *
     * @return result type
     */
    Type mul(MethodVisitor method);

    /**
     * Pop two values on top of the stack and divide the first with the second,
     * pushing the result on the stack
     *
     * @param method method visitor
     *
     * @return result type
     */
    Type div(MethodVisitor method);

    /**
     * Pop two values on top of the stack and compute the modulo of the first
     * with the second, pushing the result on the stack
     *
     * @param method method visitor
     *
     * @return result type
     */
    Type rem(MethodVisitor method);

    /**
     * Comparison with int return value, e.g. LCMP, DCMP.
     *
     * @param method the method visitor
     * @param isCmpG is this a double style cmpg
     *
     * @return int return value
     */
    Type cmp(MethodVisitor method, boolean isCmpG);
}
