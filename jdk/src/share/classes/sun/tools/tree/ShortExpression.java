package sun.tools.tree;

import sun.tools.java.*;
import java.io.PrintStream;

/**
 * WARNING: The contents of this source file are not part of any
 * supported API.  Code that depends on them does so at its own risk:
 * they are subject to change or removal without notice.
 */
public
class ShortExpression extends IntegerExpression {
    /**
     * Constructor
     */
    public ShortExpression(long where, short value) {
        super(SHORTVAL, where, Type.tShort, value);
    }

    /**
     * Print
     */
    public void print(PrintStream out) {
        out.print(value + "s");
    }
}
