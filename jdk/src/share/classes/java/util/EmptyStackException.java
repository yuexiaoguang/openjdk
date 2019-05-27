package java.util;

/**
 * Thrown by methods in the <code>Stack</code> class to indicate
 * that the stack is empty.
 *
 * @since   JDK1.0
 */
public
class EmptyStackException extends RuntimeException {
    private static final long serialVersionUID = 5084686378493302095L;

    /**
     * Constructs a new <code>EmptyStackException</code> with <tt>null</tt>
     * as its error message string.
     */
    public EmptyStackException() {
    }
}
