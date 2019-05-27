package com.sun.jdi;

/**
 * Thrown to indicate that the requested operation cannot be
 * completed because the a mirror from one target VM is being
 * combined with a mirror from another target VM.
 */
@jdk.Exported
public class VMMismatchException extends RuntimeException {
    private static final long serialVersionUID = 289169358790459564L;
    public VMMismatchException() {
        super();
    }

    public VMMismatchException(String s) {
        super(s);
    }
}
