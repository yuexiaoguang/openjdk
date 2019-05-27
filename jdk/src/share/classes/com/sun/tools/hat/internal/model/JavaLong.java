package com.sun.tools.hat.internal.model;

/**
 * Represents a long (i.e. a long field in an instance).
 */
public class JavaLong extends JavaValue {

    long value;

    public JavaLong(long value) {
        this.value = value;
    }

    public String toString() {
        return Long.toString(value);
    }
}
