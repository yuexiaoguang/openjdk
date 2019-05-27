/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xerces.internal.dom;

import java.util.Vector;

import org.w3c.dom.DOMStringList;

/**
 * DOM Level 3
 *
 * This class implements the DOM Level 3 Core interface DOMStringList.
 */
public class DOMStringListImpl implements DOMStringList {

        //A collection of DOMString values
    private Vector fStrings;

    /**
     * Construct an empty list of DOMStringListImpl
     */
    public DOMStringListImpl() {
        fStrings = new Vector();
    }

    /**
     * Construct an empty list of DOMStringListImpl
     */
    public DOMStringListImpl(Vector params) {
        fStrings = params;
    }

        /**
         * @see org.w3c.dom.DOMStringList#item(int)
         */
        public String item(int index) {
        try {
            return (String) fStrings.elementAt(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
        }

        /**
         * @see org.w3c.dom.DOMStringList#getLength()
         */
        public int getLength() {
                return fStrings.size();
        }

        /**
         * @see org.w3c.dom.DOMStringList#contains(String)
         */
        public boolean contains(String param) {
                return fStrings.contains(param) ;
        }

    /**
     * DOM Internal:
     * Add a <code>DOMString</code> to the list.
     *
     * @param domString A string to add to the list
     */
    public void add(String param) {
        fStrings.add(param);
    }

}
