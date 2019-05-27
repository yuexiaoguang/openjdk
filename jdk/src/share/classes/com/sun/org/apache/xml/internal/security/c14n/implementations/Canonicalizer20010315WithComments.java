package com.sun.org.apache.xml.internal.security.c14n.implementations;

import com.sun.org.apache.xml.internal.security.c14n.Canonicalizer;

public class Canonicalizer20010315WithComments extends Canonicalizer20010315 {

    /**
     * Constructor Canonicalizer20010315WithXPathWithComments
     */
    public Canonicalizer20010315WithComments() {
        super(true);
    }

    /** @inheritDoc */
    public final String engineGetURI() {
        return Canonicalizer.ALGO_ID_C14N_WITH_COMMENTS;
    }

    /** @inheritDoc */
    public final boolean engineGetIncludeComments() {
        return true;
    }
}
