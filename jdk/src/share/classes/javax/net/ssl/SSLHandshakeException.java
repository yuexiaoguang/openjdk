package javax.net.ssl;


/**
 * Indicates that the client and server could not negotiate the
 * desired level of security.  The connection is no longer usable.
 */
public
class SSLHandshakeException extends SSLException
{
    private static final long serialVersionUID = -5045881315018326890L;

    /**
     * Constructs an exception reporting an error found by
     * an SSL subsystem during handshaking.
     *
     * @param reason describes the problem.
     */
    public SSLHandshakeException(String reason)
    {
        super(reason);
    }
}
