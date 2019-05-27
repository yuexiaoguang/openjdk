package com.sun.tools.hat.internal.model;

/**
 * Represents a short (i.e. a short field in an instance).
 */
public class JavaShort extends JavaValue {

    short value;

    public JavaShort(short value) {
        this.value = value;
    }

    public String toString() {
        return "" + value;
    }

}
