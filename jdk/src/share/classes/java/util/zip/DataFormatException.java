package java.util.zip;

/**
 * Signals that a data format error has occurred.
 */
public
class DataFormatException extends Exception {
    private static final long serialVersionUID = 2219632870893641452L;

    /**
     * Constructs a DataFormatException with no detail message.
     */
    public DataFormatException() {
        super();
    }

    /**
     * Constructs a DataFormatException with the specified detail message.
     * A detail message is a String that describes this particular exception.
     * @param s the String containing a detail message
     */
    public DataFormatException(String s) {
        super(s);
    }
}
