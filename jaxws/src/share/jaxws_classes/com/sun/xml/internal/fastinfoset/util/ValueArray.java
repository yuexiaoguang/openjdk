package com.sun.xml.internal.fastinfoset.util;

public abstract class ValueArray {
    public static final int DEFAULT_CAPACITY = 10;
    public static final int MAXIMUM_CAPACITY = Integer.MAX_VALUE;

    protected int _size;

    protected int _readOnlyArraySize;

    protected int _maximumCapacity;

    public int getSize() {
        return _size;
    }

    public int getMaximumCapacity() {
        return _maximumCapacity;
    }

    public void setMaximumCapacity(int maximumCapacity) {
        _maximumCapacity = maximumCapacity;
    }

    public abstract void setReadOnlyArray(ValueArray array, boolean clear);

    public abstract void clear();
}
