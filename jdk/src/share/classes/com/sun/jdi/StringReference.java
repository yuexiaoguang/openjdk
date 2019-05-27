package com.sun.jdi;

/**
 * A string object from the target VM.
 * A StringReference is an {@link ObjectReference} with additional
 * access to string-specific information from the target VM.
 */
@jdk.Exported
public interface StringReference extends ObjectReference {
    /**
     * Returns the StringReference as a String. The returned string
     * is the equivalent of the mirrored string, but is an entity in the
     * client VM and can be manipulated like any other string.
     *
     * @return the string value.
     */
    String value();
}
