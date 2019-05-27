package com.sun.xml.internal.xsom;

import com.sun.xml.internal.xsom.visitor.XSContentTypeFunction;
import com.sun.xml.internal.xsom.visitor.XSContentTypeVisitor;

/**
 * Content of a complex type.
 */
public interface XSContentType extends XSComponent
{
    /**
     * Equivalent of <code>(this instanceof XSSimpleType)?this:null</code>
     */
    XSSimpleType asSimpleType();
    /**
     * Equivalent of <code>(this instanceof XSParticle)?this:null</code>
     */
    XSParticle asParticle();
    /**
     * If this content type represents the empty content, return <code>this</code>,
     * otherwise null.
     */
    XSContentType asEmpty();

    <T> T apply( XSContentTypeFunction<T> function );
    void visit( XSContentTypeVisitor visitor );
}
