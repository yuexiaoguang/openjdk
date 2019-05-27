package com.sun.tools.hat.internal.oql;

/**
 * OQLException is thrown if OQL execution results in error
 */
public class OQLException extends Exception {
    public OQLException(String msg) {
        super(msg);
    }

    public OQLException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public OQLException(Throwable cause) {
        super(cause);
    }
}
