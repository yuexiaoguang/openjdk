/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xerces.internal.impl.dtd;

import com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentFilter;

/**
 * Defines a DTD Validator filter to allow
 * components to query the DTD validator.
 */
public interface XMLDTDValidatorFilter
    extends XMLDocumentFilter {

    /**
     * Returns true if the validator has a DTD grammar
     *
     * @return true if the validator has a DTD grammar
     */
    public boolean hasGrammar();

    /**
     * Return true if validator must validate the document
     *
     * @return true if validator must validate the document
     */
    public boolean validate();


} // interface XMLDTDValidatorFilter
