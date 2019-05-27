package com.sun.jdi;

/**
 * Thrown to indicate an operation cannot be completed because
 * it is not valid for a native method.
 */
@jdk.Exported
public class NativeMethodException extends RuntimeException {

    private static final long serialVersionUID = 3924951669039469992L;
    public NativeMethodException() {
        super();
    }

    public NativeMethodException(String message) {
        super(message);
    }
}
