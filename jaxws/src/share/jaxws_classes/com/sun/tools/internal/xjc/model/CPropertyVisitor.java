package com.sun.tools.internal.xjc.model;

/**
 * Visitor for {@link CPropertyInfo}.
 *
 * Ideally it should be defined on the runtime core model, but the runtime is on diet.
 * Hence it's defined here.
 */
public interface CPropertyVisitor<V> {
    V onElement( CElementPropertyInfo p );
    V onAttribute( CAttributePropertyInfo p );
    V onValue( CValuePropertyInfo p );
    V onReference( CReferencePropertyInfo p );
}
