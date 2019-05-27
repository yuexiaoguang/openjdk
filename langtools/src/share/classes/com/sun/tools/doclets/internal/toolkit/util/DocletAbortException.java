package com.sun.tools.doclets.internal.toolkit.util;

/**
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 */
public class DocletAbortException extends RuntimeException {
    private static final long serialVersionUID = -9131058909576418984L;

    public DocletAbortException(String message) {
        super(message);
    }

    public DocletAbortException(Throwable cause) {
        super(cause);
    }
}
