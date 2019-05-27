/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xalan.internal.xsltc.dom;

import com.sun.org.apache.xalan.internal.xsltc.DOM;

public interface StripWhitespaceFilter {
    public boolean stripSpace(DOM dom, int node, int type);
}
