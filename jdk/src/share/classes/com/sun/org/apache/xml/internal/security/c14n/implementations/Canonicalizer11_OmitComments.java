package com.sun.org.apache.xml.internal.security.c14n.implementations;

import com.sun.org.apache.xml.internal.security.c14n.Canonicalizer;

public class Canonicalizer11_OmitComments extends Canonicalizer11 {

    public Canonicalizer11_OmitComments() {
        super(false);
    }

    public final String engineGetURI() {
        return Canonicalizer.ALGO_ID_C14N11_OMIT_COMMENTS;
    }

    public final boolean engineGetIncludeComments() {
        return false;
    }
}
