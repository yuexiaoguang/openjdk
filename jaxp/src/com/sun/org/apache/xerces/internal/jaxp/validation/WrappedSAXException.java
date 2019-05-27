/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xerces.internal.jaxp.validation;

import org.xml.sax.SAXException;

/**
 * Wraps {@link SAXException} and make it an unchecked exception.
 * <p>
 * Xerces XNI doesn't allow {@link SAXException} to be thrown.
 * So when the user-supplied error handler throws it,
 * it needs to be tunneled through Xerces.
 *
 * <p>
 * It is a bug if this exception "leaks" to the application.
 *
 * FIXME: use XNIException for this purpose. It's already doing this
 * kind of SAXException tunneling.
 */
public class WrappedSAXException extends RuntimeException {
    public final SAXException exception;

    WrappedSAXException( SAXException e ) {
        this.exception = e;
    }
}
