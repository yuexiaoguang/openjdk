package com.sun.xml.internal.txw2;

/**
 * Signals an incorrect use of TXW annotations.
 */
public class IllegalAnnotationException extends TxwException {
    public IllegalAnnotationException(String message) {
        super(message);
    }

    public IllegalAnnotationException(Throwable cause) {
        super(cause);
    }

    public IllegalAnnotationException(String message, Throwable cause) {
        super(message, cause);
    }

    private static final long serialVersionUID = 1L;
}
