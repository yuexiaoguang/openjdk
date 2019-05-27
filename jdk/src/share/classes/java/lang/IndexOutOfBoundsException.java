package java.lang;

/**
 * Thrown to indicate that an index of some sort (such as to an array, to a
 * string, or to a vector) is out of range.
 * <p>
 * Applications can subclass this class to indicate similar exceptions.
 */
public
class IndexOutOfBoundsException extends RuntimeException {
    private static final long serialVersionUID = 234122996006267687L;

    /**
     * Constructs an <code>IndexOutOfBoundsException</code> with no
     * detail message.
     */
    public IndexOutOfBoundsException() {
        super();
    }

    /**
     * Constructs an <code>IndexOutOfBoundsException</code> with the
     * specified detail message.
     *
     * @param   s   the detail message.
     */
    public IndexOutOfBoundsException(String s) {
        super(s);
    }
}
