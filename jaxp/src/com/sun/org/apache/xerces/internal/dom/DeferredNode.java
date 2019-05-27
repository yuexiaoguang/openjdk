/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xerces.internal.dom;

import org.w3c.dom.Node;

/**
 * An interface for deferred node object.
 */
public interface DeferredNode extends Node {


     public static final short TYPE_NODE  =  20;

    //
    // DeferredNode methods
    //

    /** Returns the node index. */
    public int getNodeIndex();

} // interface DeferredNode
