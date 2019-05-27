package com.sun.org.apache.xml.internal.security.keys.keyresolver.implementations;

import java.security.PublicKey;
import java.security.cert.X509Certificate;

import com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;
import com.sun.org.apache.xml.internal.security.keys.content.x509.XMLX509Certificate;
import com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolverException;
import com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolverSpi;
import com.sun.org.apache.xml.internal.security.keys.storage.StorageResolver;
import com.sun.org.apache.xml.internal.security.utils.Constants;
import com.sun.org.apache.xml.internal.security.utils.XMLUtils;
import org.w3c.dom.Element;

/**
 * Resolves Certificates which are directly contained inside a
 * <CODE>ds:X509Certificate</CODE> Element.
 */
public class X509CertificateResolver extends KeyResolverSpi {

    /** {@link org.apache.commons.logging} logging facility */
    private static java.util.logging.Logger log =
        java.util.logging.Logger.getLogger(X509CertificateResolver.class.getName());

    /**
     * Method engineResolvePublicKey
     * @inheritDoc
     * @param element
     * @param BaseURI
     * @param storage
     *
     * @throws KeyResolverException
     */
    public PublicKey engineLookupAndResolvePublicKey(
        Element element, String BaseURI, StorageResolver storage
    ) throws KeyResolverException {

        X509Certificate cert =
            this.engineLookupResolveX509Certificate(element, BaseURI, storage);

        if (cert != null) {
            return cert.getPublicKey();
        }

        return null;
    }

    /**
     * Method engineResolveX509Certificate
     * @inheritDoc
     * @param element
     * @param BaseURI
     * @param storage
     *
     * @throws KeyResolverException
     */
    public X509Certificate engineLookupResolveX509Certificate(
        Element element, String BaseURI, StorageResolver storage
    ) throws KeyResolverException {

        try {
            Element[] els =
                XMLUtils.selectDsNodes(element.getFirstChild(), Constants._TAG_X509CERTIFICATE);
            if ((els == null) || (els.length == 0)) {
                Element el =
                    XMLUtils.selectDsNode(element.getFirstChild(), Constants._TAG_X509DATA, 0);
                if (el != null) {
                    return engineLookupResolveX509Certificate(el, BaseURI, storage);
                }
                return null;
            }

            // populate Object array
            for (int i = 0; i < els.length; i++) {
                XMLX509Certificate xmlCert = new XMLX509Certificate(els[i], BaseURI);
                X509Certificate cert = xmlCert.getX509Certificate();
                if (cert != null) {
                    return cert;
                }
            }
            return null;
        } catch (XMLSecurityException ex) {
            if (log.isLoggable(java.util.logging.Level.FINE)) {
                log.log(java.util.logging.Level.FINE, "XMLSecurityException", ex);
            }
            throw new KeyResolverException("generic.EmptyMessage", ex);
        }
    }

    /**
     * Method engineResolveSecretKey
     * @inheritDoc
     * @param element
     * @param BaseURI
     * @param storage
     */
    public javax.crypto.SecretKey engineLookupAndResolveSecretKey(
        Element element, String BaseURI, StorageResolver storage
    ) {
        return null;
    }
}
