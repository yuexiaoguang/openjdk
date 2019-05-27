/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xerces.internal.dom;

import java.io.Serializable;

/**
 * This class is used, via a pool managed on CoreDocumentImpl, in ParentNode to
 * improve performance of the NodeList accessors, getLength() and item(i).
 */
class NodeListCache implements Serializable {

    /** Serialization version. */
    private static final long serialVersionUID = -7927529254918631002L;

    /** Cached node list length. */
    int fLength = -1;

    /** Last requested node index. */
    int fChildIndex = -1;

    /** Last requested node. */
    ChildNode fChild;

    /** Owner of this cache */
    ParentNode fOwner;

    /** Pointer to the next object on the list,
        only meaningful when actully stored in the free list. */
    NodeListCache next;

    NodeListCache(ParentNode owner) {
        fOwner = owner;
    }
}
