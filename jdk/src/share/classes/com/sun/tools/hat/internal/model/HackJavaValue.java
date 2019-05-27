package com.sun.tools.hat.internal.model;

/**
 * This is used to represent values that the program doesn't really understand.
 * This includes the null vlaue, and unresolved references (which shouldn't
 * happen in well-formed hprof files).
 */
public class HackJavaValue extends JavaValue {

    private String value;
    private int size;

    public HackJavaValue(String value, int size) {
        this.value = value;
        this.size = size;
    }

    public String toString() {
        return value;
    }

    public int getSize() {
        return size;
    }

}
