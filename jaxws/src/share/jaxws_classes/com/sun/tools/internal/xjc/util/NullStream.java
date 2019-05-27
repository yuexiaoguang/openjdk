package com.sun.tools.internal.xjc.util;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Just consumes the byte stream. Kind of like /dev/null.
 */
public class NullStream extends OutputStream {

    public void write(int b) throws IOException {
    }

    public void close() throws IOException {
    }

    public void flush() throws IOException {
    }

    public void write(byte[] b, int off, int len) throws IOException {
    }

    public void write(byte[] b) throws IOException {
    }
}
