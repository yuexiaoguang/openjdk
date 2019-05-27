/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xalan.internal.xsltc.runtime;

public final class Operators {
    public static final int EQ = 0;
    public static final int NE = 1;
    public static final int GT = 2;
    public static final int LT = 3;
    public static final int GE = 4;
    public static final int LE = 5;

    private static final String[] names = {
    "=", "!=", ">", "<", ">=", "<="
    };

    public static final String getOpNames(int operator) {
          return names[operator];
    }

//  Swap operator array
    private static final int[] swapOpArray = {
        EQ,     // EQ
        NE,     // NE
        LT,     // GT
        GT,     // LT
        LE,     // GE
        GE      // LE
    };

    public static final int swapOp(int operator) {
          return swapOpArray[operator];
    }

}
