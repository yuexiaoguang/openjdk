package com.sun.jdi;

/**
 * Provides access to a primitive <code>short</code> value in
 * the target VM.
 */
@jdk.Exported
public interface ShortValue extends PrimitiveValue, Comparable<ShortValue> {

    /**
     * Returns this ShortValue as a short.
     *
     * @return the <code>short</code> mirrored by this object.
     */
    short value();

    /**
     * Compares the specified Object with this ShortValue for equality.
     *
     * @return true if the Object is a ShortValue and if applying "=="
     * to the two mirrored primitives would evaluate to true; false
     * otherwise.
     */
    boolean equals(Object obj);

    /**
     * Returns the hash code value for this ShortValue.
     *
     * @return the integer hash code
     */
    int hashCode();
}
