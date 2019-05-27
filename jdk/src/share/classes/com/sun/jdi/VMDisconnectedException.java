package com.sun.jdi;

/**
 * Unchecked exception thrown to indicate that the
 * requested operation cannot be
 * completed because there is no longer a connection to the target VM.
 */
@jdk.Exported
public class VMDisconnectedException extends RuntimeException {

    private static final long serialVersionUID = 2892975269768351637L;
    public VMDisconnectedException() {
        super();
    }
    public VMDisconnectedException(String message) {
        super(message);
    }
}
