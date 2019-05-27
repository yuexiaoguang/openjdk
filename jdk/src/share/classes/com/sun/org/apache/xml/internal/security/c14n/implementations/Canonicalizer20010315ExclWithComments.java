package com.sun.org.apache.xml.internal.security.c14n.implementations;

import com.sun.org.apache.xml.internal.security.c14n.Canonicalizer;

/**
 * Class Canonicalizer20010315ExclWithComments
 */
public class Canonicalizer20010315ExclWithComments extends Canonicalizer20010315Excl {

    /**
     * Constructor Canonicalizer20010315ExclWithComments
     *
     */
    public Canonicalizer20010315ExclWithComments() {
        super(true);
    }

    /** @inheritDoc */
    public final String engineGetURI() {
        return Canonicalizer.ALGO_ID_C14N_EXCL_WITH_COMMENTS;
    }

    /** @inheritDoc */
    public final boolean engineGetIncludeComments() {
        return true;
    }
}
