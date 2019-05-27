package com.sun.media.sound;

import java.io.IOException;

/**
 * This exception is used when a file contains illegal or unexpected data.
 */
public class InvalidDataException extends IOException {

    private static final long serialVersionUID = 1L;

    public InvalidDataException() {
        super("Invalid Data!");
    }

    public InvalidDataException(String s) {
        super(s);
    }
}
