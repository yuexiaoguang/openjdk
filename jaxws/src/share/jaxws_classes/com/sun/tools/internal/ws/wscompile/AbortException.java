package com.sun.tools.internal.ws.wscompile;

/**
 * Signals the abortion of the compilation.
 * <p>
 * This exception should be only thrown from {@link ErrorReceiver}
 * for the consistent error handling.
 */
public class AbortException extends RuntimeException {
    public AbortException() {
    }
}
