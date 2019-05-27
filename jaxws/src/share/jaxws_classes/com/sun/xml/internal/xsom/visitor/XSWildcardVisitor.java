package com.sun.xml.internal.xsom.visitor;

import com.sun.xml.internal.xsom.XSWildcard;

/**
 * Visits three kinds of {@link XSWildcard}.
 */
public interface XSWildcardVisitor {
    void any( XSWildcard.Any wc );
    void other( XSWildcard.Other wc );
    void union( XSWildcard.Union wc );
}
