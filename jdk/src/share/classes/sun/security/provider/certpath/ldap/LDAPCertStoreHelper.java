package sun.security.provider.certpath.ldap;

import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidAlgorithmParameterException;
import java.security.cert.CertStore;
import java.security.cert.CertStoreException;
import java.security.cert.X509CertSelector;
import java.security.cert.X509CRLSelector;
import javax.naming.CommunicationException;
import javax.naming.ServiceUnavailableException;
import javax.security.auth.x500.X500Principal;

import sun.security.provider.certpath.CertStoreHelper;

/**
 * LDAP implementation of CertStoreHelper.
 */
public final class LDAPCertStoreHelper
    extends CertStoreHelper
{
    @Override
    public CertStore getCertStore(URI uri)
        throws NoSuchAlgorithmException, InvalidAlgorithmParameterException
    {
        return LDAPCertStore.getInstance(LDAPCertStore.getParameters(uri));
    }

    @Override
    public X509CertSelector wrap(X509CertSelector selector,
                                 X500Principal certSubject,
                                 String ldapDN)
        throws IOException
    {
        return new LDAPCertStore.LDAPCertSelector(selector, certSubject, ldapDN);
    }

    @Override
    public X509CRLSelector wrap(X509CRLSelector selector,
                                Collection<X500Principal> certIssuers,
                                String ldapDN)
        throws IOException
    {
        return new LDAPCertStore.LDAPCRLSelector(selector, certIssuers, ldapDN);
    }

    @Override
    public boolean isCausedByNetworkIssue(CertStoreException e) {
        Throwable t = e.getCause();
        return (t != null && (t instanceof ServiceUnavailableException ||
                              t instanceof CommunicationException));
    }
}
