package sun.net.www.protocol.https;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * <code>HostnameVerifier</code> provides a callback mechanism so that
 * implementers of this interface can supply a policy for
 * handling the case where the host to connect to and
 * the server name from the certificate mismatch.
 *
 * The default implementation will deny such connections.
 */
final public class DefaultHostnameVerifier implements HostnameVerifier {
    public boolean verify(String hostname, SSLSession session) {
        return false;
    }
}
