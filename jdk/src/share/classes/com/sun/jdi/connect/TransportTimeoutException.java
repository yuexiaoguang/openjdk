package com.sun.jdi.connect;

/**
 * This exception may be thrown as a result of a timeout
 * when attaching to a target VM, or waiting to accept a
 * connection from a target VM.
 *
 * <p> When attaching to a target VM, using {@link
 * AttachingConnector#attach attach} this
 * exception may be thrown if the connector supports a timeout
 * {@link Connector.Argument connector argument}. Similiarly,
 * when waiting to accept a connection from a target VM,
 * using {@link ListeningConnector#accept accept} this
 * exception may be thrown if the connector supports a
 * timeout connector argument when accepting.
 *
 * <p> In addition, for developers creating {@link
 * com.sun.jdi.connect.spi.TransportService TransportService}
 * implementations this exception is thrown when
 * {@link com.sun.jdi.connect.spi.TransportService#attach attach}
 * times out when establishing a connection to a target VM,
 * or {@link com.sun.jdi.connect.spi.TransportService#accept
 * accept} times out while waiting for a target VM to connect. </p>
 */
@jdk.Exported
public class TransportTimeoutException extends java.io.IOException {
    private static final long serialVersionUID = 4107035242623365074L;
    /**
     * Constructs a <tt>TransportTimeoutException</tt> with no detail
     * message.
     */
    public TransportTimeoutException() {
    }


    /**
     * Constructs a <tt>TransportTimeoutException</tt> with the
     * specified detail message.
     *
     * @param message the detail message pertaining to this exception.
     */
    public TransportTimeoutException(String message) {
        super(message);
    }

}
