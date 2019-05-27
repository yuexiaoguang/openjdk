package com.sun.istack.internal.localization;

/**
 * Localizable message.
 */
public interface Localizable {
    /**
     * Gets the key in the resource bundle.
     *
     * @return
     *      if this method returns {@link #NOT_LOCALIZABLE},
     *      that means the message is not localizable, and
     *      the first item of {@link #getArguments()} array
     *      holds a String.
     */
    public String getKey();

    /**
     * Returns the arguments for message formatting.
     *
     * @return
     *      can be an array of length 0 but never be null.
     */
    public Object[] getArguments();
    public String getResourceBundleName();


    /**
     * Special constant that represents a message that
     * is not localizable.
     *
     * <p>
     * Use of "new" is to create an unique instance.
     */
    public static final String NOT_LOCALIZABLE = "\u0000";
}
