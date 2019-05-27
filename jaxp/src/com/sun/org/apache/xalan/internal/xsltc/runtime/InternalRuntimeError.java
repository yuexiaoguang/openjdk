/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xalan.internal.xsltc.runtime;

/**
 * Class to express failed assertions and similar for the xsltc runtime.
 * As java.lang.AssertionError was introduced in JDK 1.4 we can't use that yet.
 */
public class InternalRuntimeError extends Error {

    public InternalRuntimeError(String message) {
        super(message);
    }

}
