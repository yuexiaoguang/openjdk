package com.sun.org.apache.xerces.internal.util;

import java.io.PrintWriter;

import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLErrorHandler;
import com.sun.org.apache.xerces.internal.xni.parser.XMLParseException;

/**
 * Default error handler.
 */
public class DefaultErrorHandler
    implements XMLErrorHandler {

    //
    // Data
    //

    /** Print writer. */
    protected PrintWriter fOut;

    //
    // Constructors
    //

    /**
     * Constructs an error handler that prints error messages to
     * <code>System.err</code>.
     */
    public DefaultErrorHandler() {
        this(new PrintWriter(System.err));
    } // <init>()

    /**
     * Constructs an error handler that prints error messages to the
     * specified <code>PrintWriter</code.
     */
    public DefaultErrorHandler(PrintWriter out) {
        fOut = out;
    } // <init>(PrintWriter)

    //
    // ErrorHandler methods
    //

    /** Warning. */
    public void warning(String domain, String key, XMLParseException ex)
        throws XNIException {
        printError("Warning", ex);
    } // warning(XMLParseException)

    /** Error. */
    public void error(String domain, String key, XMLParseException ex)
        throws XNIException {
        printError("Error", ex);
    } // error(XMLParseException)

    /** Fatal error. */
    public void fatalError(String domain, String key, XMLParseException ex)
        throws XNIException {
        printError("Fatal Error", ex);
        throw ex;
    } // fatalError(XMLParseException)

    //
    // Private methods
    //

    /** Prints the error message. */
    private void printError(String type, XMLParseException ex) {

        fOut.print("[");
        fOut.print(type);
        fOut.print("] ");
        String systemId = ex.getExpandedSystemId();
        if (systemId != null) {
            int index = systemId.lastIndexOf('/');
            if (index != -1)
                systemId = systemId.substring(index + 1);
            fOut.print(systemId);
        }
        fOut.print(':');
        fOut.print(ex.getLineNumber());
        fOut.print(':');
        fOut.print(ex.getColumnNumber());
        fOut.print(": ");
        fOut.print(ex.getMessage());
        fOut.println();
        fOut.flush();

    } // printError(String,SAXParseException)

} // class DefaultErrorHandler
