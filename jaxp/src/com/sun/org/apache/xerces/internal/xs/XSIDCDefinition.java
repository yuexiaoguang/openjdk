/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xerces.internal.xs;

/**
 * This interface represents the Identity-constraint Definition schema
 * component.
 */
public interface XSIDCDefinition extends XSObject {
    // Identity Constraints
    /**
     * See the definition of <code>key</code> in the identity-constraint
     * category.
     */
    public static final short IC_KEY                    = 1;
    /**
     * See the definition of <code>keyref</code> in the identity-constraint
     * category.
     */
    public static final short IC_KEYREF                 = 2;
    /**
     * See the definition of <code>unique</code> in the identity-constraint
     * category.
     */
    public static final short IC_UNIQUE                 = 3;

    /**
     * [identity-constraint category]: one of key, keyref or unique.
     */
    public short getCategory();

    /**
     * [selector]: a restricted XPath 1.0 expression.
     */
    public String getSelectorStr();

    /**
     * [fields]: a non-empty list of restricted  XPath 1.0 expressions.
     */
    public StringList getFieldStrs();

    /**
     * [referenced key]: required if [identity-constraint category] is keyref,
     * <code>null</code> otherwise. An identity-constraint definition with [
     * identity-constraint category] equal to key or unique.
     */
    public XSIDCDefinition getRefKey();

    /**
     * A sequence of [annotations] or an empty  <code>XSObjectList</code>.
     */
    public XSObjectList getAnnotations();

}
