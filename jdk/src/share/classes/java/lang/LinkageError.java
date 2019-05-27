package java.lang;

/**
 * Subclasses of {@code LinkageError} indicate that a class has
 * some dependency on another class; however, the latter class has
 * incompatibly changed after the compilation of the former class.
 */
public
class LinkageError extends Error {
    private static final long serialVersionUID = 3579600108157160122L;

    /**
     * Constructs a {@code LinkageError} with no detail message.
     */
    public LinkageError() {
        super();
    }

    /**
     * Constructs a {@code LinkageError} with the specified detail
     * message.
     *
     * @param   s   the detail message.
     */
    public LinkageError(String s) {
        super(s);
    }

    /**
     * Constructs a {@code LinkageError} with the specified detail
     * message and cause.
     *
     * @param s     the detail message.
     * @param cause the cause, may be {@code null}
     * @since 1.7
     */
    public LinkageError(String s, Throwable cause) {
        super(s, cause);
    }
}
