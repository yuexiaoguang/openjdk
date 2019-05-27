package com.sun.beans.decoder;

/**
 * This interface represents the result of method execution.
 */
public interface ValueObject {

    /**
     * Returns the result of method execution.
     *
     * @return the result of method execution
     */
    Object getValue();

    /**
     * Returns {@code void} state of this value object.
     *
     * @return {@code true} if value can be ignored,
     *         {@code false} otherwise
     */
    boolean isVoid();
}
