/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xerces.internal.impl.xs.identity;

import com.sun.org.apache.xerces.internal.xs.XSIDCDefinition;

/**
 * Schema key reference identity constraint.
 */
public class KeyRef
    extends IdentityConstraint {

    //
    // Data
    //

    /** The key (or unique) being referred to. */
    protected UniqueOrKey fKey;

    //
    // Constructors
    //

    /** Constructs a keyref with the specified name. */
    public KeyRef(String namespace, String identityConstraintName,
                  String elemName, UniqueOrKey key) {
        super(namespace, identityConstraintName, elemName);
        fKey = key;
        type = IC_KEYREF;
    } // <init>(String,String,String)

    //
    // Public methods
    //

    /** Returns the key being referred to.  */
    public UniqueOrKey getKey() {
        return fKey;
    } // getKey(): int

    /**
     * {referenced key} Required if {identity-constraint category} is keyref,
     * forbidden otherwise. An identity-constraint definition with
     * {identity-constraint category} equal to key or unique.
     */
    public XSIDCDefinition getRefKey() {
        return fKey;
    }

} // class KeyRef
