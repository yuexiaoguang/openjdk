package com.sun.tools.hat.internal.model;

/**
 * Abstract base class for all value types (ints, longs, floats, etc.)
 */
public abstract class JavaValue extends JavaThing {

    protected JavaValue() {
    }

    public boolean isHeapAllocated() {
        return false;
    }

    abstract public String toString();

    public int getSize() {
        // The size of a value is already accounted for in the class
        // that has the data member.
        return 0;
    }

}
