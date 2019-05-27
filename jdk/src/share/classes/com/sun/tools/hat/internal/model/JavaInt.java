package com.sun.tools.hat.internal.model;

/**
 * Represents an integer (i.e. an int field in an instance).
 */
public class JavaInt extends JavaValue {

    int value;

    public JavaInt(int value) {
        this.value = value;
    }

    public String toString() {
        return "" + value;
    }

}
