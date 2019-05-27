package com.sun.xml.internal.xsom.visitor;

import com.sun.xml.internal.xsom.XSContentType;
import com.sun.xml.internal.xsom.XSParticle;
import com.sun.xml.internal.xsom.XSSimpleType;

/**
 * Function object that works on {@link XSContentType}.
 */
public interface XSContentTypeFunction<T> {
    T simpleType( XSSimpleType simpleType );
    T particle( XSParticle particle );
    T empty( XSContentType empty );
}
