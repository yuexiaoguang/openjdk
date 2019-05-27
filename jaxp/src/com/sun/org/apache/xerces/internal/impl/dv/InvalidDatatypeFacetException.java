/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xerces.internal.impl.dv;

/**
 * Datatype exception for invalid facet. This exception is only used by
 * schema datatypes.
 */
public class InvalidDatatypeFacetException extends DatatypeException {

    /** Serialization version. */
    static final long serialVersionUID = -4104066085909970654L;

    /**
     * Create a new datatype exception by providing an error code and a list
     * of error message substitution arguments.
     *
     * @param key  error code
     * @param args error arguments
     */
    public InvalidDatatypeFacetException(String key, Object[] args) {
        super(key, args);
    }

}
