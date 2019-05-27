package javax.sound.sampled;

/**
 * A <code>LineUnavailableException</code> is an exception indicating that a
 * line cannot be opened because it is unavailable.  This situation
 * arises most commonly when a requested line is already in use
 * by another application.
 */
/*
 * A <code>LinenavailableException</code> is an exception indicating that a
 * line annot be opened because it is unavailable.  This situation
 * arises most commonly when a line is requested when it is already in use
 * by another application.
 */
public class LineUnavailableException extends Exception {

    /**
     * Constructs a <code>LineUnavailableException</code> that has
     * <code>null</code> as its error detail message.
     */
    public LineUnavailableException() {

        super();
    }


    /**
     * Constructs a <code>LineUnavailableException</code> that has
     * the specified detail message.
     *
     * @param message a string containing the error detail message
     */
    public LineUnavailableException(String message) {

        super(message);
    }
}
