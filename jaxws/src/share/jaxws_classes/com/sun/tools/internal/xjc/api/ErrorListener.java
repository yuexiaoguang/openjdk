package com.sun.tools.internal.xjc.api;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

/**
 * Implemented by the driver of the compiler engine to handle
 * errors found during the compiliation.
 *
 * <p>
 * This class implements {@link ErrorHandler} so it can be
 * passed to anywhere where {@link ErrorHandler} is expected.
 *
 * <p>
 * However, to make the error handling easy (and make it work
 * with visitor patterns nicely), this interface is not allowed
 * to abort the processing. It merely receives errors.
 */
public interface ErrorListener extends com.sun.xml.internal.bind.api.ErrorListener {
    void error(SAXParseException exception);
    void fatalError(SAXParseException exception);
    void warning(SAXParseException exception);
    /**
     * Used to report possibly verbose information that
     * can be safely ignored.
     */
    void info(SAXParseException exception);
}
