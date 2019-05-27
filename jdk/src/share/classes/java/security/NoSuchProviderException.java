package java.security;

/**
 * This exception is thrown when a particular security provider is
 * requested but is not available in the environment.
 */
public class NoSuchProviderException extends GeneralSecurityException {

    private static final long serialVersionUID = 8488111756688534474L;

    /**
     * Constructs a NoSuchProviderException with no detail message. A
     * detail message is a String that describes this particular
     * exception.
     */
    public NoSuchProviderException() {
        super();
    }

    /**
     * Constructs a NoSuchProviderException with the specified detail
     * message. A detail message is a String that describes this
     * particular exception.
     *
     * @param msg the detail message.
     */
    public NoSuchProviderException(String msg) {
        super(msg);
    }
}
