package java.io;

/**
 * Indicates that one or more deserialized objects failed validation
 * tests.  The argument should provide the reason for the failure.
 */
public class InvalidObjectException extends ObjectStreamException {

    private static final long serialVersionUID = 3233174318281839583L;

    /**
     * Constructs an <code>InvalidObjectException</code>.
     * @param reason Detailed message explaining the reason for the failure.
     *
     * @see ObjectInputValidation
     */
    public  InvalidObjectException(String reason) {
        super(reason);
    }
}
