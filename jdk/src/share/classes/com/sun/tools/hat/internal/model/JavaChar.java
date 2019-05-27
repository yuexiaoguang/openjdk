package com.sun.tools.hat.internal.model;

/**
 * Represents a char (i.e. a char field in an instance).
 */
public class JavaChar extends JavaValue {

    char value;

    public JavaChar(char value) {
        this.value = value;
    }

    public String toString() {
        return "" + value;
    }

}
