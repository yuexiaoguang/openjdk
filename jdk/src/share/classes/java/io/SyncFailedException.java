package java.io;

/**
 * Signals that a sync operation has failed.
 */
public class SyncFailedException extends IOException {
    private static final long serialVersionUID = -2353342684412443330L;

    /**
     * Constructs an SyncFailedException with a detail message.
     * A detail message is a String that describes this particular exception.
     *
     * @param desc  a String describing the exception.
     */
    public SyncFailedException(String desc) {
        super(desc);
    }
}
