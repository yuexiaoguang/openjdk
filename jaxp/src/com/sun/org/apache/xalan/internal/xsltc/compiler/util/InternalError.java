/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xalan.internal.xsltc.compiler.util;
/**
 * Marks a class of errors in which XSLTC has reached some incorrect internal
 * state from which it cannot recover.
 */
public class InternalError extends Error {
    /**
     * Construct an <code>InternalError</code> with the specified error message.
     * @param msg the error message
     */
    public InternalError(String msg) {
        super(msg);
    }
}
