/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xerces.internal.impl.dv;

import com.sun.org.apache.xerces.internal.impl.dv.ValidationContext;

/**
 * The interface that a DTD datatype must implement. The implementation of this
 * interface must be thread-safe.
 */
public interface DatatypeValidator {

    /**
     * validate a given string against this DV
     *
     * @param content       the string value that needs to be validated
     * @param context       the validation context
     */
    public void validate(String content, ValidationContext context)
        throws InvalidDatatypeValueException;

}
