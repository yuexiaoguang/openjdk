package com.sun.org.apache.xml.internal.security.utils;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * This {@link org.xml.sax.ErrorHandler} does absolutely nothing but log
 * the events.
 */
public class IgnoreAllErrorHandler implements ErrorHandler {

    /** {@link org.apache.commons.logging} logging facility */
    private static java.util.logging.Logger log =
        java.util.logging.Logger.getLogger(IgnoreAllErrorHandler.class.getName());

    /** Field throwExceptions */
    private static final boolean warnOnExceptions =
        System.getProperty("com.sun.org.apache.xml.internal.security.test.warn.on.exceptions", "false").equals("true");

    /** Field throwExceptions           */
    private static final boolean throwExceptions =
        System.getProperty("com.sun.org.apache.xml.internal.security.test.throw.exceptions", "false").equals("true");


    /** @inheritDoc */
    public void warning(SAXParseException ex) throws SAXException {
        if (IgnoreAllErrorHandler.warnOnExceptions) {
            log.log(java.util.logging.Level.WARNING, "", ex);
        }
        if (IgnoreAllErrorHandler.throwExceptions) {
            throw ex;
        }
    }


    /** @inheritDoc */
    public void error(SAXParseException ex) throws SAXException {
        if (IgnoreAllErrorHandler.warnOnExceptions) {
            log.log(java.util.logging.Level.SEVERE, "", ex);
        }
        if (IgnoreAllErrorHandler.throwExceptions) {
            throw ex;
        }
    }


    /** @inheritDoc */
    public void fatalError(SAXParseException ex) throws SAXException {
        if (IgnoreAllErrorHandler.warnOnExceptions) {
            log.log(java.util.logging.Level.WARNING, "", ex);
        }
        if (IgnoreAllErrorHandler.throwExceptions) {
            throw ex;
        }
    }
}
