package com.sun.org.apache.xerces.internal.util;

/**
 * A simple integer based stack.
 *
 * moved to com.sun.org.apache.xerces.internal.util by neilg to support the
 * XPathMatcher.
 */
public final class IntStack {

    //
    // Data
    //

    /** Stack depth. */
    private int fDepth;

    /** Stack data. */
    private int[] fData;

    //
    // Public methods
    //

    /** Returns the size of the stack. */
    public int size() {
        return fDepth;
    }

    /** Pushes a value onto the stack. */
    public void push(int value) {
        ensureCapacity(fDepth + 1);
        fData[fDepth++] = value;
    }

    /** Peeks at the top of the stack. */
    public int peek() {
        return fData[fDepth - 1];
    }

    /** Returns the element at the specified depth in the stack. */
    public int elementAt(int depth) {
        return fData[depth];
    }

    /** Pops a value off of the stack. */
    public int pop() {
        return fData[--fDepth];
    }

    /** Clears the stack. */
    public void clear() {
        fDepth = 0;
    }

    // debugging

    /** Prints the stack. */
    public void print() {
        System.out.print('(');
        System.out.print(fDepth);
        System.out.print(") {");
        for (int i = 0; i < fDepth; i++) {
            if (i == 3) {
                System.out.print(" ...");
                break;
            }
            System.out.print(' ');
            System.out.print(fData[i]);
            if (i < fDepth - 1) {
                System.out.print(',');
            }
        }
        System.out.print(" }");
        System.out.println();
    }

    //
    // Private methods
    //

    /** Ensures capacity. */
    private void ensureCapacity(int size) {
        if (fData == null) {
            fData = new int[32];
        }
        else if (fData.length <= size) {
            int[] newdata = new int[fData.length * 2];
            System.arraycopy(fData, 0, newdata, 0, fData.length);
            fData = newdata;
        }
    }

} // class IntStack
