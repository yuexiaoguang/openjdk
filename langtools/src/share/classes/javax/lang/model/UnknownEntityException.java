package javax.lang.model;

/**
 * Superclass of exceptions which indicate that an unknown kind of
 * entity was encountered.  This situation can occur if the language
 * evolves and new kinds of constructs are introduced.  Subclasses of
 * this exception may be thrown by visitors to indicate that the
 * visitor was created for a prior version of the language.
 *
 * <p>A common superclass for those exceptions allows a single catch
 * block to have code handling them uniformly.
 */
public class UnknownEntityException extends RuntimeException {

    private static final long serialVersionUID = 269L;

    /**
     * Creates a new {@code UnknownEntityException} with the specified
     * detail message.
     *
     * @param message the detail message
     */
    protected UnknownEntityException(String message) {
        super(message);
    }
}
