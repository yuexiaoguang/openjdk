package com.sun.jdi;

/**
 * Thrown to indicate that the requested operation cannot be
 * completed because the target VM has run out of memory.
 */
@jdk.Exported
public class VMOutOfMemoryException extends RuntimeException {
    private static final long serialVersionUID = 71504228548910686L;
    public VMOutOfMemoryException() {
        super();
    }

    public VMOutOfMemoryException(String s) {
        super(s);
    }
}
