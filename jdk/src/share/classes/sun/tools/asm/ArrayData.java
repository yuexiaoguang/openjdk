package sun.tools.asm;

import sun.tools.java.*;

/**
 * WARNING: The contents of this source file are not part of any
 * supported API.  Code that depends on them does so at its own risk:
 * they are subject to change or removal without notice.
 */
public final
class ArrayData {
    Type type;
    int nargs;

    public ArrayData(Type type, int nargs) {
        this.type = type;
        this.nargs = nargs;
    }
}
