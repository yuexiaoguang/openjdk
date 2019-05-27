package com.sun.media.sound;

/**
 * This exception is used when a reader is used to read file of a format
 * it doesn't unterstand or support.
 */
public class InvalidFormatException extends InvalidDataException {

    private static final long serialVersionUID = 1L;

    public InvalidFormatException() {
        super("Invalid format!");
    }

    public InvalidFormatException(String s) {
        super(s);
    }
}
