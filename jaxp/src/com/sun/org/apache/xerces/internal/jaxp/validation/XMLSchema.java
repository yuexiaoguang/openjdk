/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xerces.internal.jaxp.validation;

import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool;

/**
 * <p>Implementation of Schema for W3C XML Schemas.</p>
 */
final class XMLSchema extends AbstractXMLSchema {

    /** The grammar pool is immutable */
    private final XMLGrammarPool fGrammarPool;

    /** Constructor */
    public XMLSchema(XMLGrammarPool grammarPool) {
        fGrammarPool = grammarPool;
    }

    /*
     * XSGrammarPoolContainer methods
     */

    /**
     * <p>Returns the grammar pool contained inside the container.</p>
     *
     * @return the grammar pool contained inside the container
     */
    public XMLGrammarPool getGrammarPool() {
        return fGrammarPool;
    }

    /**
     * <p>Returns whether the schema components contained in this object
     * can be considered to be a fully composed schema and should be
     * used to exclusion of other schema components which may be
     * present elsewhere.</p>
     *
     * @return whether the schema components contained in this object
     * can be considered to be a fully composed schema
     */
    public boolean isFullyComposed() {
        return true;
    }

} // XMLSchema
