package com.sun.tools.doclets.internal.toolkit.util.links;

/**
 * Stores output of a link.
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 */
public interface LinkOutput {

    /**
     * Append the given object to the output.
     *
     * @param o the object to append.
     */
    public void append(Object o);

    /**
     * Insert the given object into the output sequence.
     *
     * @param offset the offset.
     * @param o the object to be inserted.
     */
    public void insert(int offset, Object o);
}
