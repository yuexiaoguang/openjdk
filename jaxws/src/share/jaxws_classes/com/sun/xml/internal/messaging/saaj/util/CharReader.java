package com.sun.xml.internal.messaging.saaj.util;

import java.io.CharArrayReader;

// This class just gives access to the underlying buffer without copying.
public class CharReader extends CharArrayReader {
    public CharReader(char buf[], int length) {
        super(buf, 0, length);
    }

    public CharReader(char buf[], int offset, int length) {
        super(buf, offset, length);
    }

    public char[] getChars() {
        return buf;
    }

    public int getCount() {
        return count;
    }
}
