package sun.tools.tree;

import sun.tools.java.*;
import sun.tools.asm.Assembler;

/**
 * WARNING: The contents of this source file are not part of any
 * supported API.  Code that depends on them does so at its own risk:
 * they are subject to change or removal without notice.
 */
public
class AssignShiftRightExpression extends AssignOpExpression {
    /**
     * Constructor
     */
    public AssignShiftRightExpression(long where, Expression left, Expression right) {
        super(ASGRSHIFT, where, left, right);
    }

    /**
     * Code
     */
    void codeOperation(Environment env, Context ctx, Assembler asm) {
        asm.add(where, opc_ishr + itype.getTypeCodeOffset());
    }
}
