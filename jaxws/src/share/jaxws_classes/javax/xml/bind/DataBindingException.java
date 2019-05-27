package javax.xml.bind;

/**
 * Exception that represents a failure in a JAXB operation.
 *
 * <p>
 * This exception differs from {@link JAXBException} in that
 * this is an unchecked exception, while <tt>JAXBException</tt>
 * is a checked exception.
 */
public class DataBindingException extends RuntimeException {
    public DataBindingException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataBindingException(Throwable cause) {
        super(cause);
    }
}
