package com.sun.jdi;

/**
 * Provides access to a primitive <code>boolean</code> value in
 * the target VM.
 */
@jdk.Exported
public interface BooleanValue extends PrimitiveValue {

    /**
     * Returns this BooleanValue as a boolean.
     *
     * @return the <code>boolean</code> mirrored by this object.
     */
    boolean value();

    /**
     * Compares the specified Object with this BooleanValue for equality.
     *
     * @return true if the Object is a BooleanValue and if applying "=="
     * to the two mirrored primitives would evaluate to true; false
     * otherwise.
     */
    boolean equals(Object obj);

    /**
     * Returns the hash code value for this BooleanValue.
     *
     * @return the integer hash code
     */
    int hashCode();
}
