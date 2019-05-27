/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xerces.internal.impl.xs.identity;

/**
 * Schema unique or key identity constraint.
 * These two kinds of identity constraint have been combined to save
 * the creation of a separate Vector object for any element that
 * has both.  A short int is used to distinguish which this object is.
 */
public class UniqueOrKey
    extends IdentityConstraint {

    //
    // Constructors
    //

    /** Constructs a unique or a key identity constraint. */
    public UniqueOrKey(String namespace, String identityConstraintName,
                       String elemName, short type) {
        super(namespace, identityConstraintName, elemName);
        this.type = type;
    } // <init>(String,String)

    //
    // Public methods
    //

} // class Unique
