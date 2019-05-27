/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xerces.internal.xs;

/**
 * This interface represents the Attribute Group Definition schema component.
 */
public interface XSAttributeGroupDefinition extends XSObject {
    /**
     * A set of [attribute uses] if it exists, otherwise an empty
     * <code>XSObjectList</code>.
     */
    public XSObjectList getAttributeUses();

    /**
     * A [wildcard] if it exists, otherwise <code>null</code>.
     */
    public XSWildcard getAttributeWildcard();

    /**
     * An annotation if it exists, otherwise <code>null</code>. If not null
     * then the first [annotation] from the sequence of annotations.
     */
    public XSAnnotation getAnnotation();

    /**
     * A sequence of [annotations] or an empty <code>XSObjectList</code>.
     */
    public XSObjectList getAnnotations();
}
