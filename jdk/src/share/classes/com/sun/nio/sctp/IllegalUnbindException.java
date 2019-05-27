package com.sun.nio.sctp;

/**
 * Unchecked exception thrown when an attempt is made to remove an
 * address that is not bound to the channel, or remove an address from a
 * channel that has only one address bound to it.
 */
@jdk.Exported
public class IllegalUnbindException extends IllegalStateException {
    private static final long serialVersionUID = -310540883995532224L;

    /**
     * Constructs an instance of this class.
     */
    public IllegalUnbindException() { }

    /**
     * Constructs an instance of this class with the specified detailed message.
     *
     * @param  msg
     *         The String that contains a detailed message
     */
    public IllegalUnbindException(String msg) {
        super(msg);
    }
}

