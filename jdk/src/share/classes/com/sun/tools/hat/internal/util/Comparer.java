package com.sun.tools.hat.internal.util;

/**
 * Base class for comparison of two objects.
 */
abstract public class Comparer {

    /**
     * @return a number <, == or > 0 depending on lhs compared to rhs
     * @see java.lang.String.compareTo
    **/
    abstract public int compare(Object lhs, Object rhs);
}
