package com.sun.jdi;

/**
 * Thrown to indicate that the requested operation cannot be
 * completed because the specified line number is not valid.
 *
 * @deprecated This exception is no longer thrown
 */
@jdk.Exported
@Deprecated
public class InvalidLineNumberException extends RuntimeException {
    private static final long serialVersionUID = 4048709912372692875L;
    public InvalidLineNumberException() {
        super();
    }

    public InvalidLineNumberException(String s) {
        super(s);
    }
}
