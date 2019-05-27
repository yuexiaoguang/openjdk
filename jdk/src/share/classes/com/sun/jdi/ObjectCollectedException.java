package com.sun.jdi;

/**
 * Thrown to indicate that the requested operation cannot be
 * completed because the specified object has been garbage collected.
 */
@jdk.Exported
public class ObjectCollectedException extends RuntimeException {
    private static final long serialVersionUID = -1928428056197269588L;
    public ObjectCollectedException() {
        super();
    }

    public ObjectCollectedException(String s) {
        super(s);
    }
}
