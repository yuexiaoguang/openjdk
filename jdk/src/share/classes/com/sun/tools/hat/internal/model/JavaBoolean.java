package com.sun.tools.hat.internal.model;

/**
 * Represents a boolean (i.e. a boolean field in an instance).
 */
public class JavaBoolean extends JavaValue {

    boolean value;

    public JavaBoolean(boolean value) {
        this.value = value;
    }

    public String toString() {
        return "" + value;
    }

}
