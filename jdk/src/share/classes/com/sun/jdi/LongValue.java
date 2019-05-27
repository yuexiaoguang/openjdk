package com.sun.jdi;

/**
 * Provides access to a primitive <code>long</code> value in
 * the target VM.
 */
@jdk.Exported
public interface LongValue extends PrimitiveValue, Comparable<LongValue> {

    /**
     * Returns this LongValue as a long.
     *
     * @return the <code>long</code> mirrored by this object.
     */
    long value();

    /**
     * Compares the specified Object with this LongValue for equality.
     *
     * @return true if the Object is a LongValue and if applying "=="
     * to the two mirrored primitives would evaluate to true; false
     * otherwise.
     */
    boolean equals(Object obj);

    /**
     * Returns the hash code value for this LongValue.
     *
     * @return the integer hash code
     */
    int hashCode();
}
