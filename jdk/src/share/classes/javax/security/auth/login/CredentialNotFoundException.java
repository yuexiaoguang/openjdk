package javax.security.auth.login;

/**
 * Signals that a credential was not found.
 *
 * <p> This exception may be thrown by a LoginModule if it is unable
 * to locate a credential necessary to perform authentication.
 */
public class CredentialNotFoundException extends CredentialException {

    private static final long serialVersionUID = -7779934467214319475L;

    /**
     * Constructs a CredentialNotFoundException with no detail message.
     * A detail message is a String that describes this particular exception.
     */
    public CredentialNotFoundException() {
        super();
    }

    /**
     * Constructs a CredentialNotFoundException with the specified
     * detail message. A detail message is a String that describes
     * this particular exception.
     *
     * <p>
     *
     * @param msg the detail message.
     */
    public CredentialNotFoundException(String msg) {
        super(msg);
    }
}
