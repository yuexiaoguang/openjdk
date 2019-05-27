package com.sun.xml.internal.ws.spi.db;

/**
 * Signals an error in Databinding.
 */
public class DatabindingException extends RuntimeException {
        public DatabindingException() {}
    public DatabindingException(String message) { super(message); }
    public DatabindingException(Throwable cause) { super(cause); }
    public DatabindingException(String message, Throwable cause) { super(message, cause); }
}
