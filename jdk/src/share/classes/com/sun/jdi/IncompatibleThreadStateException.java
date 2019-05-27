package com.sun.jdi;

/**
 * Thrown to indicate that the requested operation cannot be
 * completed while the specified thread is in its current state.
 */
@jdk.Exported
public class IncompatibleThreadStateException extends Exception {
    private static final long serialVersionUID = 6199174323414551389L;

    public IncompatibleThreadStateException() {
        super();
    }

    public IncompatibleThreadStateException(String s) {
        super(s);
    }
}
