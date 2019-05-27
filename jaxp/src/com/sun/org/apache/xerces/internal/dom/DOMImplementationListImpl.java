/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xerces.internal.dom;

import java.util.Vector;
import org.w3c.dom.DOMImplementationList;
import org.w3c.dom.DOMImplementation;

/**
 * <p>This class implements the DOM Level 3 Core interface DOMImplementationList.</p>
 */
public class DOMImplementationListImpl implements DOMImplementationList {

    //A collection of DOMImplementations
    private Vector fImplementations;

    /**
     * Construct an empty list of DOMImplementations
     */
    public DOMImplementationListImpl() {
        fImplementations = new Vector();
    }

    /**
     * Construct an empty list of DOMImplementations
     */
    public DOMImplementationListImpl(Vector params) {
        fImplementations = params;
    }

    /**
     * Returns the indexth item in the collection.
     *
     * @param index The index of the DOMImplemetation from the list to return.
     */
    public DOMImplementation item(int index) {
        try {
            return (DOMImplementation) fImplementations.elementAt(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * Returns the number of DOMImplementations in the list.
     *
     * @return An integer indicating the number of DOMImplementations.
     */
    public int getLength() {
        return fImplementations.size();
    }
}
