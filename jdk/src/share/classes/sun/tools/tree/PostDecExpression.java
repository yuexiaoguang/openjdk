package sun.tools.tree;

import sun.tools.java.*;
import sun.tools.asm.Assembler;

/**
 * WARNING: The contents of this source file are not part of any
 * supported API.  Code that depends on them does so at its own risk:
 * they are subject to change or removal without notice.
 */
public
class PostDecExpression extends IncDecExpression {
    /**
     * Constructor
     */
    public PostDecExpression(long where, Expression right) {
        super(POSTDEC, where, right);
    }

    /**
     * Code
     */
    public void codeValue(Environment env, Context ctx, Assembler asm) {
        codeIncDec(env, ctx, asm, false, false, true);
    }
    public void code(Environment env, Context ctx, Assembler asm) {
        codeIncDec(env, ctx, asm, false, false, false);
    }
}
