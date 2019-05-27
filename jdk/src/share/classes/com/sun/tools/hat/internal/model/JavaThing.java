package com.sun.tools.hat.internal.model;

import java.util.Enumeration;
import java.util.Hashtable;


/**
 * Represents a java "Thing".  A thing is anything that can be the value of
 * a field.  This includes JavaHeapObject, JavaObjectRef, and JavaValue.
 */
public abstract class JavaThing {

    protected JavaThing() {
    }

    /**
     * If this is a forward reference, figure out what it really
     * refers to.
     *
     * @param snapshot  The snapshot this is for
     * @param field     The field this thing represents.  If null, it is
     *                  assumed this thing is an object (and never a value).
     */
    public JavaThing dereference(Snapshot shapshot, JavaField field) {
        return this;
    }


    /**
     * Are we the same type as other?
     *
     * @see JavaObject.isSameTypeAs()
     */
    public boolean isSameTypeAs(JavaThing other) {
        return getClass() == other.getClass();
    }
    /**
     * @return true iff this represents a heap-allocated object
     */
    abstract public boolean isHeapAllocated();

    /**
     * @return the size of this object, in bytes, including VM overhead
     */
    abstract public int getSize();

    /**
     * @return a human-readable string representation of this thing
     */
    abstract public String toString();

    /**
     * Compare our string representation to other's
     * @see java.lang.String.compareTo()
     */
    public int compareTo(JavaThing other) {
        return toString().compareTo(other.toString());
    }

}
