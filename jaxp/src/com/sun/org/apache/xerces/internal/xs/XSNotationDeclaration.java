/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xerces.internal.xs;

/**
 *  This interface represents the Notation Declaration schema component.
 */
public interface XSNotationDeclaration extends XSObject {
    /**
     *  The URI reference representing the system identifier for the notation
     * declaration, if present, <code>null</code> otherwise.
     */
    public String getSystemId();

    /**
     *  The string representing the public identifier for this notation
     * declaration, if present; <code>null</code> otherwise.
     */
    public String getPublicId();

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
