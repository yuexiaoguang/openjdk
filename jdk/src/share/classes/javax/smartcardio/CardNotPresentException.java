package javax.smartcardio;

/**
 * Exception thrown when an application tries to establish a connection with a
 * terminal that has no card present.
 */
public class CardNotPresentException extends CardException {

    private final static long serialVersionUID = 1346879911706545215L;

    /**
     * Constructs a new CardNotPresentException with the specified detail message.
     *
     * @param message the detail message
     */
    public CardNotPresentException(String message) {
        super(message);
    }

    /**
     * Constructs a new CardNotPresentException with the specified cause and a detail message
     * of <code>(cause==null ? null : cause.toString())</code>.
     *
     * @param cause the cause of this exception or null
     */
    public CardNotPresentException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new CardNotPresentException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause of this exception or null
     */
    public CardNotPresentException(String message, Throwable cause) {
        super(message, cause);
    }
}
