package com.sun.jdi;

/**
 * Thrown to indicate that the requested operation cannot be
 * completed because the specified code index is not valid.
 *
 * @deprecated This exception is no longer thrown
 */
@jdk.Exported
@Deprecated
public class InvalidCodeIndexException extends RuntimeException {
    private static final long serialVersionUID = 7416010225133747805L;
    public InvalidCodeIndexException() {
        super();
    }

    public InvalidCodeIndexException(String s) {
        super(s);
    }
}
