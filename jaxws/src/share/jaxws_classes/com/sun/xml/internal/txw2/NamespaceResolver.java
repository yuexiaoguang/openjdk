package com.sun.xml.internal.txw2;

/**
 * Used by {@link DatatypeWriter} to declare additional namespaces.
 */
public interface NamespaceResolver {
    /**
     * Allocates a prefix for the specified URI and returns it.
     *
     * @param nsUri
     *      the namespace URI to be declared. Can be empty but must not be null.
     * @return
     *      the empty string if the URI is bound to the default namespace.
     *      Otherwise a non-empty string that represents a prefix.
     */
    String getPrefix(String nsUri);
}
