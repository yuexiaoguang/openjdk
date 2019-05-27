package jdk.nashorn.internal.runtime;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * This class represents a string composed of two parts which may themselves be
 * instances of <tt>ConsString</tt> or {@link String}. Copying of characters to
 * a proper string is delayed until it becomes necessary.
 */
public final class ConsString implements CharSequence {

    private CharSequence left, right;
    final private int length;
    private boolean flat = false;

    /**
     * Constructor
     *
     * Takes two {@link CharSequence} instances that, concatenated, forms this {@code ConsString}
     *
     * @param left  left char sequence
     * @param right right char sequence
     */
    public ConsString(final CharSequence left, final CharSequence right) {
        assert left instanceof String || left instanceof ConsString;
        assert right instanceof String || right instanceof ConsString;
        this.left = left;
        this.right = right;
        length = left.length() + right.length();
    }

    @Override
    public String toString() {
        return (String) flattened();
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public char charAt(final int index) {
        return flattened().charAt(index);
    }

    @Override
    public CharSequence subSequence(final int start, final int end) {
        return flattened().subSequence(start, end);
    }

    private CharSequence flattened() {
        if (!flat) {
            flatten();
        }
        return left;
    }

    private void flatten() {
        // We use iterative traversal as recursion may exceed the stack size limit.
        final char[] chars = new char[length];
        int pos = length;
        // Strings are most often composed by appending to the end, which causes ConsStrings
        // to be very unbalanced, with mostly single string elements on the right and a long
        // linear list on the left. Traversing from right to left helps to keep the stack small
        // in this scenario.
        final Deque<CharSequence> stack = new ArrayDeque<>();
        stack.addFirst(left);
        CharSequence cs = right;

        do {
            if (cs instanceof ConsString) {
                final ConsString cons = (ConsString) cs;
                stack.addFirst(cons.left);
                cs = cons.right;
            } else {
                final String str = (String) cs;
                pos -= str.length();
                str.getChars(0, str.length(), chars, pos);
                cs = stack.isEmpty() ? null : stack.pollFirst();
            }
        } while (cs != null);

        left = new String(chars);
        right = "";
        flat = true;
    }

}
