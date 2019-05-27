package com.sun.xml.internal.ws.wsdl.parser;

import javax.xml.ws.WebServiceException;


/**
 * listen to static errors found during building a the WSDL Model.
 */
public interface ErrorHandler {
    /**
     * Receives a notification for an error in the annotated code.
     */
    void error( Throwable e );
}
