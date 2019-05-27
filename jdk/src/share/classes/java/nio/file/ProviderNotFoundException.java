package java.nio.file;

/**
 * Runtime exception thrown when a provider of the required type cannot be found.
 */
public class ProviderNotFoundException
    extends RuntimeException
{
    static final long serialVersionUID = -1880012509822920354L;

    /**
     * Constructs an instance of this class.
     */
    public ProviderNotFoundException() {
    }

    /**
     * Constructs an instance of this class.
     *
     * @param   msg
     *          the detail message
     */
    public ProviderNotFoundException(String msg) {
        super(msg);
    }
}
