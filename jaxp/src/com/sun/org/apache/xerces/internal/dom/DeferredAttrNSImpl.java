/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/*
 * WARNING: because java doesn't support multi-inheritance some code is
 * duplicated. If you're changing this file you probably want to change
 * DeferredAttrImpl.java at the same time.
 */
package com.sun.org.apache.xerces.internal.dom;

/**
 * DeferredAttrNSImpl is to AttrNSImpl, what DeferredAttrImpl is to
 * AttrImpl.
 */
public final class DeferredAttrNSImpl
    extends AttrNSImpl
    implements DeferredNode {

    //
    // Constants
    //

    /** Serialization version. */
    static final long serialVersionUID = 6074924934945957154L;

    //
    // Data
    //

    /** Node index. */
    protected transient int fNodeIndex;

    //
    // Constructors
    //

    /**
     * This is the deferred constructor. Only the fNodeIndex is given here.
     * All other data, can be requested from the ownerDocument via the index.
     */
    DeferredAttrNSImpl(DeferredDocumentImpl ownerDocument, int nodeIndex) {
        super(ownerDocument, null);

        fNodeIndex = nodeIndex;
        needsSyncData(true);
        needsSyncChildren(true);

    } // <init>(DeferredDocumentImpl,int)

    //
    // DeferredNode methods
    //

    /** Returns the node index. */
    public int getNodeIndex() {
        return fNodeIndex;
    }

    //
    // Protected methods
    //

    /** Synchronizes the data (name and value) for fast nodes. */
    protected void synchronizeData() {

        // no need to sync in the future
        needsSyncData(false);

        // fluff data
        DeferredDocumentImpl ownerDocument =
            (DeferredDocumentImpl) ownerDocument();
        name = ownerDocument.getNodeName(fNodeIndex);

        // extract prefix and local part from QName
        int index = name.indexOf(':');
        if (index < 0) {
            localName = name;
        }
        else {
            localName = name.substring(index + 1);
        }

        int extra = ownerDocument.getNodeExtra(fNodeIndex);
        isSpecified((extra & SPECIFIED) != 0);
        isIdAttribute((extra & ID) != 0);

        namespaceURI = ownerDocument.getNodeURI(fNodeIndex);

        int extraNode = ownerDocument.getLastChild(fNodeIndex);
        type = ownerDocument.getTypeInfo(extraNode);
    } // synchronizeData()

    /**
     * Synchronizes the node's children with the internal structure.
     * Fluffing the children at once solves a lot of work to keep
     * the two structures in sync. The problem gets worse when
     * editing the tree -- this makes it a lot easier.
     */
    protected void synchronizeChildren() {
        DeferredDocumentImpl ownerDocument =
            (DeferredDocumentImpl) ownerDocument();
        ownerDocument.synchronizeChildren(this, fNodeIndex);
    } // synchronizeChildren()

} // class DeferredAttrImpl
