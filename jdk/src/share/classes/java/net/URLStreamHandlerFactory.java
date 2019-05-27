package java.net;

/**
 * This interface defines a factory for {@code URL} stream
 * protocol handlers.
 * <p>
 * It is used by the {@code URL} class to create a
 * {@code URLStreamHandler} for a specific protocol.
 */
public interface URLStreamHandlerFactory {
    /**
     * Creates a new {@code URLStreamHandler} instance with the specified
     * protocol.
     *
     * @param   protocol   the protocol ("{@code ftp}",
     *                     "{@code http}", "{@code nntp}", etc.).
     * @return  a {@code URLStreamHandler} for the specific protocol.
     * @see     java.net.URLStreamHandler
     */
    URLStreamHandler createURLStreamHandler(String protocol);
}
