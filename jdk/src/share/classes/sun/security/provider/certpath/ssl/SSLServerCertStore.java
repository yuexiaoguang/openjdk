package sun.security.provider.certpath.ssl;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Provider;
import java.security.cert.CertificateException;
import java.security.cert.CertSelector;
import java.security.cert.CertStore;
import java.security.cert.CertStoreException;
import java.security.cert.CertStoreParameters;
import java.security.cert.CertStoreSpi;
import java.security.cert.CRLSelector;
import java.security.cert.X509Certificate;
import java.security.cert.X509CRL;
import java.net.Socket;
import java.net.URLConnection;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509ExtendedTrustManager;

/**
 * A CertStore that retrieves an SSL server's certificate chain.
 */
public final class SSLServerCertStore extends CertStoreSpi {

    private final URI uri;
    private final static GetChainTrustManager trustManager;
    private final static SSLSocketFactory socketFactory;
    private final static HostnameVerifier hostnameVerifier;

    static {
        trustManager = new GetChainTrustManager();
        hostnameVerifier = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        SSLSocketFactory tempFactory;
        try {
            SSLContext context = SSLContext.getInstance("SSL");
            context.init(null, new TrustManager[] { trustManager }, null);
            tempFactory = context.getSocketFactory();
        } catch (GeneralSecurityException gse) {
            tempFactory = null;
        }

        socketFactory = tempFactory;
    }

    SSLServerCertStore(URI uri) throws InvalidAlgorithmParameterException {
        super(null);
        this.uri = uri;
    }

    public Collection<X509Certificate> engineGetCertificates
            (CertSelector selector) throws CertStoreException {

        try {
            URLConnection urlConn = uri.toURL().openConnection();
            if (urlConn instanceof HttpsURLConnection) {
                if (socketFactory == null) {
                    throw new CertStoreException(
                        "No initialized SSLSocketFactory");
                }

                HttpsURLConnection https = (HttpsURLConnection)urlConn;
                https.setSSLSocketFactory(socketFactory);
                https.setHostnameVerifier(hostnameVerifier);
                synchronized (trustManager) {
                    try {
                        https.connect();
                        return getMatchingCerts(
                            trustManager.serverChain, selector);
                    } catch (IOException ioe) {
                        // If the server certificate has already been
                        // retrieved, don't mind the connection state.
                        if (trustManager.exchangedServerCerts) {
                            return getMatchingCerts(
                                trustManager.serverChain, selector);
                        }

                        // otherwise, rethrow the exception
                        throw ioe;
                    } finally {
                        trustManager.cleanup();
                    }
                }
            }
        } catch (IOException ioe) {
            throw new CertStoreException(ioe);
        }

        return Collections.<X509Certificate>emptySet();
    }

    private static List<X509Certificate> getMatchingCerts
        (List<X509Certificate> certs, CertSelector selector)
    {
        // if selector not specified, all certs match
        if (selector == null) {
            return certs;
        }
        List<X509Certificate> matchedCerts = new ArrayList<>(certs.size());
        for (X509Certificate cert : certs) {
            if (selector.match(cert)) {
                matchedCerts.add(cert);
            }
        }
        return matchedCerts;
    }

    public Collection<X509CRL> engineGetCRLs(CRLSelector selector)
        throws CertStoreException
    {
        throw new UnsupportedOperationException();
    }

    static CertStore getInstance(URI uri)
        throws InvalidAlgorithmParameterException
    {
        return new CS(new SSLServerCertStore(uri), null, "SSLServer", null);
    }

    /*
     * An X509ExtendedTrustManager that ignores the server certificate
     * validation.
     */
    private static class GetChainTrustManager
            extends X509ExtendedTrustManager {

        private List<X509Certificate> serverChain =
                        Collections.<X509Certificate>emptyList();
        private boolean exchangedServerCerts = false;

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain,
                String authType) throws CertificateException {

            throw new UnsupportedOperationException();
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType,
                Socket socket) throws CertificateException {

            throw new UnsupportedOperationException();
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType,
                SSLEngine engine) throws CertificateException {

            throw new UnsupportedOperationException();
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain,
                String authType) throws CertificateException {

            exchangedServerCerts = true;
            this.serverChain = (chain == null)
                           ? Collections.<X509Certificate>emptyList()
                           : Arrays.<X509Certificate>asList(chain);

        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType,
                Socket socket) throws CertificateException {

            checkServerTrusted(chain, authType);
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType,
                SSLEngine engine) throws CertificateException {

            checkServerTrusted(chain, authType);
        }

        void cleanup() {
            exchangedServerCerts = false;
            serverChain = Collections.<X509Certificate>emptyList();
        }
    }

    /**
     * This class allows the SSLServerCertStore to be accessed as a CertStore.
     */
    private static class CS extends CertStore {
        protected CS(CertStoreSpi spi, Provider p, String type,
                     CertStoreParameters params)
        {
            super(spi, p, type, params);
        }
    }
}
