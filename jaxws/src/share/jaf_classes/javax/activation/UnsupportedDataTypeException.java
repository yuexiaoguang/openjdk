package javax.activation;

import java.io.IOException;

/**
 * Signals that the requested operation does not support the
 * requested data type.
 *
 * @since 1.6
 */
public class UnsupportedDataTypeException extends IOException {
    /**
     * Constructs an UnsupportedDataTypeException with no detail
     * message.
     */
    public UnsupportedDataTypeException() {
        super();
    }

    /**
     * Constructs an UnsupportedDataTypeException with the specified
     * message.
     *
     * @param s The detail message.
     */
    public UnsupportedDataTypeException(String s) {
        super(s);
    }
}
