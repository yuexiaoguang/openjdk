package com.sun.jdi;

/**
 * Thrown to indicate that an unexpected internal error has
 * occurred.
 */
@jdk.Exported
public class InternalException extends RuntimeException {
     private static final long serialVersionUID = -9171606393104480607L;
     private int errorCode;

     public InternalException() {
         super();
         this.errorCode = 0;
     }

     public InternalException(String s) {
         super(s);
         this.errorCode = 0;
     }

    public InternalException(int errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public InternalException(String s, int errorCode) {
        super(s);
        this.errorCode = errorCode;
    }

    public int errorCode() {
        return errorCode;
    }
}
