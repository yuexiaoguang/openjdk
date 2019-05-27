package com.sun.jdi;

/**
 * Thrown to indicate a type mismatch in setting the value of a field
 * or variable, or in specifying the return value of a method.
 */
@jdk.Exported
public class InvalidTypeException extends Exception {
    private static final long serialVersionUID = 2256667231949650806L;

    public InvalidTypeException() {
        super();
    }

    public InvalidTypeException(String s) {
        super(s);
    }
}
