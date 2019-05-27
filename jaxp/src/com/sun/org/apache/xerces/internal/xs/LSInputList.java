/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xerces.internal.xs;

import java.util.List;

import org.w3c.dom.ls.LSInput;

/**
 *  The <code>LSInputList</code> interface provides the abstraction of an
 * ordered collection of <code>LSInput</code>s, without defining or
 * constraining how this collection is implemented.
 */
public interface LSInputList extends List {
    /**
     *  The number of <code>LSInput</code>s in the list. The range of valid
     * child object indices is 0 to <code>length-1</code> inclusive.
     */
    public int getLength();

    /**
     *  Returns the <code>index</code>th item in the collection or
     * <code>null</code> if <code>index</code> is greater than or equal to
     * the number of objects in the list. The index starts at 0.
     * @param index  index into the collection.
     * @return  The <code>LSInput</code> at the <code>index</code>th
     *   position in the <code>LSInputList</code>, or <code>null</code> if
     *   the index specified is not valid.
     */
    public LSInput item(int index);

}
