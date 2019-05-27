/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xerces.internal.xs;

/**
 * This interface represents the Particle schema component.
 */
public interface XSParticle extends XSObject {
    /**
     * [min occurs]: determines the minimum number of terms that can occur.
     */
    public int getMinOccurs();

    /**
     *  [max occurs]: determines the maximum number of terms that can occur.
     * To query for the value of unbounded use
     * <code>maxOccursUnbounded</code>. When the value of
     * <code>maxOccursUnbounded</code> is <code>true</code>, the value of
     * <code>maxOccurs</code> is unspecified.
     */
    public int getMaxOccurs();

    /**
     * [max occurs]: whether the maxOccurs value is unbounded.
     */
    public boolean getMaxOccursUnbounded();

    /**
     * [term]: one of a model group, a wildcard, or an element declaration.
     */
    public XSTerm getTerm();

    /**
     * A sequence of [annotations] or an empty <code>XSObjectList</code>.
     */
    public XSObjectList getAnnotations();
}
