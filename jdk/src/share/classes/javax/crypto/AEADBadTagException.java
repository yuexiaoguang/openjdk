package javax.crypto;

/**
 * This exception is thrown when a {@link Cipher} operating in
 * an AEAD mode (such as GCM/CCM) is unable to verify the supplied
 * authentication tag.
 *
 * @since 1.7
 */
public class AEADBadTagException extends BadPaddingException {

    private static final long serialVersionUID = -488059093241685509L;

    /**
     * Constructs a AEADBadTagException with no detail message.
     */
    public AEADBadTagException() {
        super();
    }

    /**
     * Constructs a AEADBadTagException with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public AEADBadTagException(String msg) {
        super(msg);
    }
}
