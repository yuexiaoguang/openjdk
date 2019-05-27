package com.sun.xml.internal.messaging.saaj.soap;

import java.io.*;

import com.sun.xml.internal.messaging.saaj.SOAPExceptionImpl;

public class SOAPIOException extends IOException {
    SOAPExceptionImpl soapException;

    public SOAPIOException() {
        super();
        soapException = new SOAPExceptionImpl();
        soapException.fillInStackTrace();
    }

    public SOAPIOException(String s) {
        super();
        soapException = new SOAPExceptionImpl(s);
        soapException.fillInStackTrace();
    }

    public SOAPIOException(String reason, Throwable cause) {
        super();
        soapException = new SOAPExceptionImpl(reason, cause);
        soapException.fillInStackTrace();
    }

    public SOAPIOException(Throwable cause) {
        super(cause.toString());
        soapException = new SOAPExceptionImpl(cause);
        soapException.fillInStackTrace();
    }

    public Throwable fillInStackTrace() {
        if (soapException != null) {
            soapException.fillInStackTrace();
        }
        return this;
    }

    public String getLocalizedMessage() {
        return soapException.getLocalizedMessage();
    }

    public String getMessage() {
        return soapException.getMessage();
    }

    public void printStackTrace() {
        soapException.printStackTrace();
    }

    public void printStackTrace(PrintStream s) {
        soapException.printStackTrace(s);
    }

    public void printStackTrace(PrintWriter s) {
        soapException.printStackTrace(s);
    }

    public String toString() {
        return soapException.toString();
    }

}
