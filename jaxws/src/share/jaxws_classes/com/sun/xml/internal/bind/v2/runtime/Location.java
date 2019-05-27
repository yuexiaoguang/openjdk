package com.sun.xml.internal.bind.v2.runtime;

/**
 * Location information for {@link IllegalAnnotationException}.
 *
 * @since JAXB 2.0 EA1
 */
// internally, Location is created from Locatable.
public interface Location {
    /**
     * Returns a human-readable string that represents this position.
     *
     * @return
     *      never null.
     */
    String toString();
}
