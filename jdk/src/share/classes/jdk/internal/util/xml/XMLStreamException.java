package jdk.internal.util.xml;

/**
 * A copy of the StAX XMLStreamException without Location support
 *
 * The base exception for unexpected processing errors.  This Exception
 * class is used to report well-formedness errors as well as unexpected
 * processing conditions.
 */
public class XMLStreamException extends Exception {
    private static final long serialVersionUID = 1L;


    protected Throwable nested;

    /**
     * Default constructor
     */
    public XMLStreamException() {
        super();
    }

    /**
     * Construct an exception with the associated message.
     *
     * @param msg the message to report
     */
    public XMLStreamException(String msg) {
        super(msg);
    }

    /**
     * Construct an exception with the associated exception
     *
     * @param th a nested exception
     */
    public XMLStreamException(Throwable th) {
        super(th);
        nested = th;
    }

    /**
     * Construct an exception with the associated message and exception
     *
     * @param th a nested exception
     * @param msg the message to report
     */
    public XMLStreamException(String msg, Throwable th) {
        super(msg, th);
        nested = th;
    }

    /**
     * Gets the nested exception.
     *
     * @return Nested exception
     */
    public Throwable getNestedException() {
        return nested;
    }
}
