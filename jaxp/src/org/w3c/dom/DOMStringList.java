package org.w3c.dom;

/**
 *  The <code>DOMStringList</code> interface provides the abstraction of an
 * ordered collection of <code>DOMString</code> values, without defining or
 * constraining how this collection is implemented. The items in the
 * <code>DOMStringList</code> are accessible via an integral index, starting
 * from 0.
 * <p>See also the <a href='http://www.w3.org/TR/2004/REC-DOM-Level-3-Core-20040407'>Document Object Model (DOM) Level 3 Core Specification</a>.
 * @since DOM Level 3
 */
public interface DOMStringList {
    /**
     *  Returns the <code>index</code>th item in the collection. If
     * <code>index</code> is greater than or equal to the number of
     * <code>DOMString</code>s in the list, this returns <code>null</code>.
     * @param index Index into the collection.
     * @return  The <code>DOMString</code> at the <code>index</code>th
     *   position in the <code>DOMStringList</code>, or <code>null</code> if
     *   that is not a valid index.
     */
    public String item(int index);

    /**
     * The number of <code>DOMString</code>s in the list. The range of valid
     * child node indices is 0 to <code>length-1</code> inclusive.
     */
    public int getLength();

    /**
     *  Test if a string is part of this <code>DOMStringList</code>.
     * @param str  The string to look for.
     * @return  <code>true</code> if the string has been found,
     *   <code>false</code> otherwise.
     */
    public boolean contains(String str);

}
