package java.time.temporal;

import java.time.DateTimeException;

/**
 * UnsupportedTemporalTypeException indicates that a ChronoField or ChronoUnit is
 * not supported for a Temporal class.
 *
 * @implSpec
 * This class is intended for use in a single thread.
 *
 * @since 1.8
 */
public class UnsupportedTemporalTypeException extends DateTimeException {

    /**
     * Serialization version.
     */
    private static final long serialVersionUID = -6158898438688206006L;

    /**
     * Constructs a new UnsupportedTemporalTypeException with the specified message.
     *
     * @param message  the message to use for this exception, may be null
     */
    public UnsupportedTemporalTypeException(String message) {
        super(message);
    }

    /**
     * Constructs a new UnsupportedTemporalTypeException with the specified message and cause.
     *
     * @param message  the message to use for this exception, may be null
     * @param cause  the cause of the exception, may be null
     */
    public UnsupportedTemporalTypeException(String message, Throwable cause) {
        super(message, cause);
    }

}
