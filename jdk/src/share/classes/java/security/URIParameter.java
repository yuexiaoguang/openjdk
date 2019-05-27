package java.security;

/**
 * A parameter that contains a URI pointing to data intended for a
 * PolicySpi or ConfigurationSpi implementation.
 */
public class URIParameter implements
        Policy.Parameters, javax.security.auth.login.Configuration.Parameters {

    private java.net.URI uri;

    /**
     * Constructs a URIParameter with the URI pointing to
     * data intended for an SPI implementation.
     *
     * @param uri the URI pointing to the data.
     *
     * @exception NullPointerException if the specified URI is null.
     */
    public URIParameter(java.net.URI uri) {
        if (uri == null) {
            throw new NullPointerException("invalid null URI");
        }
        this.uri = uri;
    }

    /**
     * Returns the URI.
     *
     * @return uri the URI.
     */
    public java.net.URI getURI() {
        return uri;
    }
}
