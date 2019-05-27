package java.util.zip;

/**
 * Signals that an unrecoverable error has occurred.
 *
 * @since   1.6
 */
public class ZipError extends InternalError {
    private static final long serialVersionUID = 853973422266861979L;

    /**
     * Constructs a ZipError with the given detail message.
     * @param s the {@code String} containing a detail message
     */
    public ZipError(String s) {
        super(s);
    }
}
