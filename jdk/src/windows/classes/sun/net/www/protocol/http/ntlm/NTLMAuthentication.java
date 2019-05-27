package sun.net.www.protocol.http.ntlm;

import java.io.IOException;
import java.net.InetAddress;
import java.net.PasswordAuthentication;
import java.net.UnknownHostException;
import java.net.URL;
import sun.net.www.HeaderParser;
import sun.net.www.protocol.http.AuthenticationInfo;
import sun.net.www.protocol.http.AuthScheme;
import sun.net.www.protocol.http.HttpURLConnection;

/**
 * NTLMAuthentication:
 */
public class NTLMAuthentication extends AuthenticationInfo {

    private static final long serialVersionUID = 100L;

    private static final NTLMAuthenticationCallback NTLMAuthCallback =
        NTLMAuthenticationCallback.getNTLMAuthenticationCallback();

    private String hostname;
    private static String defaultDomain; /* Domain to use if not specified by user */

    static {
        defaultDomain = java.security.AccessController.doPrivileged(
            new sun.security.action.GetPropertyAction("http.auth.ntlm.domain",
                                                      "domain"));
    };

    private void init0() {

        hostname = java.security.AccessController.doPrivileged(
            new java.security.PrivilegedAction<String>() {
            public String run() {
                String localhost;
                try {
                    localhost = InetAddress.getLocalHost().getHostName().toUpperCase();
                } catch (UnknownHostException e) {
                     localhost = "localhost";
                }
                return localhost;
            }
        });
        int x = hostname.indexOf ('.');
        if (x != -1) {
            hostname = hostname.substring (0, x);
        }
    }

    String username;
    String ntdomain;
    String password;

    /**
     * Create a NTLMAuthentication:
     * Username may be specified as domain<BACKSLASH>username in the application Authenticator.
     * If this notation is not used, then the domain will be taken
     * from a system property: "http.auth.ntlm.domain".
     */
    public NTLMAuthentication(boolean isProxy, URL url, PasswordAuthentication pw) {
        super(isProxy ? PROXY_AUTHENTICATION : SERVER_AUTHENTICATION,
              AuthScheme.NTLM,
              url,
              "");
        init (pw);
    }

    private void init (PasswordAuthentication pw) {
        this.pw = pw;
        if (pw != null) {
            String s = pw.getUserName();
            int i = s.indexOf ('\\');
            if (i == -1) {
                username = s;
                ntdomain = defaultDomain;
            } else {
                ntdomain = s.substring (0, i).toUpperCase();
                username = s.substring (i+1);
            }
            password = new String (pw.getPassword());
        } else {
            /* credentials will be acquired from OS */
            username = null;
            ntdomain = null;
            password = null;
        }
        init0();
    }

   /**
    * Constructor used for proxy entries
    */
    public NTLMAuthentication(boolean isProxy, String host, int port,
                                PasswordAuthentication pw) {
        super(isProxy?PROXY_AUTHENTICATION:SERVER_AUTHENTICATION,
              AuthScheme.NTLM,
              host,
              port,
              "");
        init (pw);
    }

    /**
     * @return true if this authentication supports preemptive authorization
     */
    @Override
    public boolean supportsPreemptiveAuthorization() {
        return false;
    }

    /**
     * @return true if NTLM supported transparently (no password needed, SSO)
     */
    public static boolean supportsTransparentAuth() {
        return true;
    }

    /**
     * Returns true if the given site is trusted, i.e. we can try
     * transparent Authentication.
     */
    public static boolean isTrustedSite(URL url) {
        return NTLMAuthCallback.isTrustedSite(url);
    }

    /**
     * Not supported. Must use the setHeaders() method
     */
    @Override
    public String getHeaderValue(URL url, String method) {
        throw new RuntimeException ("getHeaderValue not supported");
    }

    /**
     * Check if the header indicates that the current auth. parameters are stale.
     * If so, then replace the relevant field with the new value
     * and return true. Otherwise return false.
     * returning true means the request can be retried with the same userid/password
     * returning false means we have to go back to the user to ask for a new
     * username password.
     */
    @Override
    public boolean isAuthorizationStale (String header) {
        return false; /* should not be called for ntlm */
    }

    /**
     * Set header(s) on the given connection.
     * @param conn The connection to apply the header(s) to
     * @param p A source of header values for this connection, not used because
     *          HeaderParser converts the fields to lower case, use raw instead
     * @param raw The raw header field.
     * @return true if all goes well, false if no headers were set.
     */
    @Override
    public synchronized boolean setHeaders(HttpURLConnection conn, HeaderParser p, String raw) {

        try {
            NTLMAuthSequence seq = (NTLMAuthSequence)conn.authObj();
            if (seq == null) {
                seq = new NTLMAuthSequence (username, password, ntdomain);
                conn.authObj(seq);
            }
            String response = "NTLM " + seq.getAuthHeader (raw.length()>6?raw.substring(5):null);
            conn.setAuthenticationProperty(getHeaderName(), response);
            if (seq.isComplete()) {
                conn.authObj(null);
            }
            return true;
        } catch (IOException e) {
            conn.authObj(null);
            return false;
        }
    }

}
