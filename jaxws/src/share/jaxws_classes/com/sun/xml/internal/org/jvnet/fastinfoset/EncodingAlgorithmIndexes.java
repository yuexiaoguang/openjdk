package com.sun.xml.internal.org.jvnet.fastinfoset;

/**
 * The indexes of built-in encoding algorithms.
 *
 * <p>The indexes of the built-in encoding algorithms are specified
 * in ITU-T Rec. X.891 | ISO/IEC 24824-1 (Fast Infoset), clause
 * 10. The indexes start from 0 instead of 1 as specified.<p>
 */
public final class EncodingAlgorithmIndexes {
    public static final int HEXADECIMAL = 0;
    public static final int BASE64      = 1;
    public static final int SHORT       = 2;
    public static final int INT         = 3;
    public static final int LONG        = 4;
    public static final int BOOLEAN     = 5;
    public static final int FLOAT       = 6;
    public static final int DOUBLE      = 7;
    public static final int UUID        = 8;
    public static final int CDATA       = 9;
}
