package com.sun.jdi.request;

/**
 * Thrown to indicate a duplicate event request.
 */
@jdk.Exported
public class DuplicateRequestException extends RuntimeException {
    private static final long serialVersionUID = -3719784920313411060L;

    public DuplicateRequestException() {
        super();
    }

    public DuplicateRequestException(String s) {
        super(s);
    }
}
