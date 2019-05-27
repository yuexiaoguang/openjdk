package com.sun.xml.internal.ws.streaming;

/**
 * <p> Interface for prefix factories. </p>
 *
 * <p> A prefix factory is able to create a new prefix for a URI that
 * was encountered for the first time when writing a document
 * using an XMLWriter. </p>
 */
public interface PrefixFactory {
    /**
     * Return a brand new prefix for the given URI.
     */
    public String getPrefix(String uri);
}
