package com.sun.tools.hat.internal.model;

/**
 * Represents a float (i.e. a float field in an instance).
 */
public class JavaFloat extends JavaValue {

    float value;

    public JavaFloat(float value) {
        this.value = value;
    }

    public String toString() {
        return Float.toString(value);
    }
}
