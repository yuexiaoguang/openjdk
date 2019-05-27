/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xalan.internal.xsltc.dom;

import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;

public interface CurrentNodeListFilter {
    public abstract boolean test(int node, int position, int last, int current,
                                 AbstractTranslet translet, DTMAxisIterator iter);
}
