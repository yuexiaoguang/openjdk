package sun.security.provider.certpath.ssl;

import java.io.IOException;
import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidAlgorithmParameterException;
import java.security.cert.CertStore;
import java.security.cert.CertStoreException;
import java.security.cert.X509CertSelector;
import java.security.cert.X509CRLSelector;
import java.util.Collection;
import javax.security.auth.x500.X500Principal;

import sun.security.provider.certpath.CertStoreHelper;

/**
 * SSL implementation of CertStoreHelper.
 */
public final class SSLServerCertStoreHelper extends CertStoreHelper {

    @Override
    public CertStore getCertStore(URI uri)
        throws NoSuchAlgorithmException, InvalidAlgorithmParameterException
    {
        return SSLServerCertStore.getInstance(uri);
    }

    @Override
    public X509CertSelector wrap(X509CertSelector selector,
                                 X500Principal certSubject,
                                 String ldapDN)
        throws IOException
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public X509CRLSelector wrap(X509CRLSelector selector,
                                Collection<X500Principal> certIssuers,
                                String ldapDN)
        throws IOException
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isCausedByNetworkIssue(CertStoreException e) {
        Throwable t = e.getCause();
        return (t != null && t instanceof IOException);
    }
}
