package com.sun.xml.internal.org.jvnet.mimepull;

import java.io.IOException;

/**
 * A special IOException that indicates a failure to decode data due
 * to an error in the formatting of the data.  This allows applications
 * to distinguish decoding errors from other I/O errors.
 */
public final class DecodingException extends IOException {

    /**
     * Constructor
     */
    public DecodingException(String s) {
        super(s);
    }
}
