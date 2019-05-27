package java.net;

import java.io.IOException;

/**
 * Thrown to indicate that the IP address of a host could not be determined.
 */
public
class UnknownHostException extends IOException {
    private static final long serialVersionUID = -4639126076052875403L;

    /**
     * Constructs a new {@code UnknownHostException} with the
     * specified detail message.
     *
     * @param   host   the detail message.
     */
    public UnknownHostException(String host) {
        super(host);
    }

    /**
     * Constructs a new {@code UnknownHostException} with no detail
     * message.
     */
    public UnknownHostException() {
    }
}
