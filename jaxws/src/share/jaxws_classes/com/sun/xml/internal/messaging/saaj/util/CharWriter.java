package com.sun.xml.internal.messaging.saaj.util;

import java.io.CharArrayWriter;

// This class just gives access to the underlying buffer without copying.
public class CharWriter extends CharArrayWriter {
    public CharWriter () {
        super();
    }

    public CharWriter(int size) {
        super(size);
    }

    public char[] getChars() {
        return buf;
    }

    public int getCount() {
        return count;
    }
}
