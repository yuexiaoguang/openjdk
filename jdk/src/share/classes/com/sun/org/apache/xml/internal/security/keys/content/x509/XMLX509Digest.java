package com.sun.org.apache.xml.internal.security.keys.content.x509;

import java.security.MessageDigest;
import java.security.cert.X509Certificate;

import com.sun.org.apache.xml.internal.security.algorithms.JCEMapper;
import com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;
import com.sun.org.apache.xml.internal.security.utils.Constants;
import com.sun.org.apache.xml.internal.security.utils.Signature11ElementProxy;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Provides content model support for the <code>dsig11:X509Digest</code> element.
 */
public class XMLX509Digest extends Signature11ElementProxy implements XMLX509DataContent {

    /**
     * Constructor XMLX509Digest
     *
     * @param element
     * @param BaseURI
     * @throws XMLSecurityException
     */
    public XMLX509Digest(Element element, String BaseURI) throws XMLSecurityException {
        super(element, BaseURI);
    }

    /**
     * Constructor XMLX509Digest
     *
     * @param doc
     * @param digestBytes
     * @param algorithmURI
     */
    public XMLX509Digest(Document doc, byte[] digestBytes, String algorithmURI) {
        super(doc);
        this.addBase64Text(digestBytes);
        this.constructionElement.setAttributeNS(null, Constants._ATT_ALGORITHM, algorithmURI);
    }

    /**
     * Constructor XMLX509Digest
     *
     * @param doc
     * @param x509certificate
     * @param algorithmURI
     * @throws XMLSecurityException
     */
    public XMLX509Digest(Document doc, X509Certificate x509certificate, String algorithmURI) throws XMLSecurityException {
        super(doc);
        this.addBase64Text(getDigestBytesFromCert(x509certificate, algorithmURI));
        this.constructionElement.setAttributeNS(null, Constants._ATT_ALGORITHM, algorithmURI);
    }

    /**
     * Method getAlgorithmAttr
     *
     * @return the Algorithm attribute
     */
    public Attr getAlgorithmAttr() {
        return this.constructionElement.getAttributeNodeNS(null, Constants._ATT_ALGORITHM);
    }

    /**
     * Method getAlgorithm
     *
     * @return Algorithm string
     */
    public String getAlgorithm() {
        return this.getAlgorithmAttr().getNodeValue();
    }

    /**
     * Method getDigestBytes
     *
     * @return the digestbytes
     * @throws XMLSecurityException
     */
    public byte[] getDigestBytes() throws XMLSecurityException {
        return this.getBytesFromTextChild();
    }

    /**
     * Method getDigestBytesFromCert
     *
     * @param cert
     * @param algorithmURI
     * @return digest bytes from the given certificate
     *
     * @throws XMLSecurityException
     */
    public static byte[] getDigestBytesFromCert(X509Certificate cert, String algorithmURI) throws XMLSecurityException {
        String jcaDigestAlgorithm = JCEMapper.translateURItoJCEID(algorithmURI);
        if (jcaDigestAlgorithm == null) {
            Object exArgs[] = { algorithmURI };
            throw new XMLSecurityException("XMLX509Digest.UnknownDigestAlgorithm", exArgs);
        }

        try {
            MessageDigest md = MessageDigest.getInstance(jcaDigestAlgorithm);
            return md.digest(cert.getEncoded());
        } catch (Exception e) {
            Object exArgs[] = { jcaDigestAlgorithm };
            throw new XMLSecurityException("XMLX509Digest.FailedDigest", exArgs);
        }

    }

    /** @inheritDoc */
    public String getBaseLocalName() {
        return Constants._TAG_X509DIGEST;
    }
}
