package com.sun.org.apache.xml.internal.security.encryption;

/**
 * <code>CipherValue</code> is the wrapper for cipher text.
 */
public interface CipherValue {
    /**
     * Returns the Base 64 encoded, encrypted octets that is the
     * <code>CipherValue</code>.
     *
     * @return cipher value.
     */
    String getValue();

    /**
     * Sets the Base 64 encoded, encrypted octets that is the
     * <code>CipherValue</code>.
     *
     * @param value the cipher value.
     */
    void setValue(String value);
}
