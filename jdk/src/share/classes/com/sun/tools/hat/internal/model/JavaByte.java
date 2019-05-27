package com.sun.tools.hat.internal.model;

/**
 * Represents an byte (i.e. a byte field in an instance).
 */
public class JavaByte extends JavaValue {

    byte value;

    public JavaByte(byte value) {
        this.value = value;
    }

    public String toString() {
        return "0x" + Integer.toString(((int) value) & 0xff, 16);
    }

}
