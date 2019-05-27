package jdk.nashorn.internal.runtime.arrays;

/**
 * Mechanism for communicating that something isn't a plain
 * numeric integer array index. This enables things like
 * array getters for the fast case in a try, basically
 * just consisting of an "array[index]" access without
 * any checks of boundary conditions that rarely happen
 */
@SuppressWarnings("serial")
class InvalidArrayIndexException extends Exception {

    private final Object index;

    InvalidArrayIndexException(final Object index) {
        super(index == null ? "null" : index.toString());
        this.index = index;
    }

    InvalidArrayIndexException(final int index) {
        this(Integer.valueOf(index));
    }

    InvalidArrayIndexException(final long index) {
        this(Long.valueOf(index));
    }

    InvalidArrayIndexException(final double index) {
        this(Double.valueOf(index));
    }

    @Override
    public String toString() {
        return index.toString();
    }

    Object getIndex() {
        return index;
    }

}
