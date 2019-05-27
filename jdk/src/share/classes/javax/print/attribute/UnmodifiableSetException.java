package javax.print.attribute;

/**
 * Thrown to indicate that the requested operation cannot be performed
 * because the set is unmodifiable.
 */
public class UnmodifiableSetException extends RuntimeException {
    /**
     * Constructs an UnsupportedOperationException with no detail message.
     */
    public UnmodifiableSetException() {
    }

    /**
     * Constructs an UnmodifiableSetException with the specified
     * detail message.
     *
     * @param message the detail message
     */
    public UnmodifiableSetException(String message) {
        super(message);
    }
}
