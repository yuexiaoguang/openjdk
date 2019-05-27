package javax.net.ssl;

/**
 * Reports a bad SSL key.  Normally, this indicates misconfiguration
 * of the server or client SSL certificate and private key.
 */
public
class SSLKeyException extends SSLException
{
    private static final long serialVersionUID = -8071664081941937874L;

    /**
     * Constructs an exception reporting a key management error
     * found by an SSL subsystem.
     *
     * @param reason describes the problem.
     */
    public SSLKeyException(String reason)
    {
        super(reason);
    }
}
