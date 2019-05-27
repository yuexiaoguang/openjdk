/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xerces.internal.jaxp.validation;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * {@link ErrorHandler} that throws all errors and fatal errors.
 */
final class DraconianErrorHandler implements ErrorHandler {

    /**
     * Singleton instance.
     */
    private static final DraconianErrorHandler ERROR_HANDLER_INSTANCE
        = new DraconianErrorHandler();

    private DraconianErrorHandler() {}

    /** Returns the one and only instance of this error handler. */
    public static DraconianErrorHandler getInstance() {
        return ERROR_HANDLER_INSTANCE;
    }

    /** Warning: Ignore. */
    public void warning(SAXParseException e) throws SAXException {
        // noop
    }

    /** Error: Throws back SAXParseException. */
    public void error(SAXParseException e) throws SAXException {
        throw e;
    }

    /** Fatal Error: Throws back SAXParseException. */
    public void fatalError(SAXParseException e) throws SAXException {
        throw e;
    }

} // DraconianErrorHandler
