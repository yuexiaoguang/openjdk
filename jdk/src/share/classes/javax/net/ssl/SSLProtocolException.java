package javax.net.ssl;

/**
 * Reports an error in the operation of the SSL protocol.  Normally
 * this indicates a flaw in one of the protocol implementations.
 */
public
class SSLProtocolException extends SSLException
{
    private static final long serialVersionUID = 5445067063799134928L;

    /**
     * Constructs an exception reporting an SSL protocol error
     * detected by an SSL subsystem.
     *
     * @param reason describes the problem.
     */
    public SSLProtocolException(String reason)
    {
        super(reason);
    }
}
