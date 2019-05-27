package com.sun.org.apache.xml.internal.security.keys.content;

import java.security.PublicKey;

import com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;
import com.sun.org.apache.xml.internal.security.keys.content.keyvalues.DSAKeyValue;
import com.sun.org.apache.xml.internal.security.keys.content.keyvalues.RSAKeyValue;
import com.sun.org.apache.xml.internal.security.utils.Constants;
import com.sun.org.apache.xml.internal.security.utils.SignatureElementProxy;
import com.sun.org.apache.xml.internal.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * The KeyValue element contains a single public key that may be useful in
 * validating the signature. Structured formats for defining DSA (REQUIRED)
 * and RSA (RECOMMENDED) public keys are defined in Signature Algorithms
 * (section 6.4). The KeyValue element may include externally defined public
 * keys values represented as PCDATA or element types from an external
 * namespace.
 */
public class KeyValue extends SignatureElementProxy implements KeyInfoContent {

    /**
     * Constructor KeyValue
     *
     * @param doc
     * @param dsaKeyValue
     */
    public KeyValue(Document doc, DSAKeyValue dsaKeyValue) {
        super(doc);

        XMLUtils.addReturnToElement(this.constructionElement);
        this.constructionElement.appendChild(dsaKeyValue.getElement());
        XMLUtils.addReturnToElement(this.constructionElement);
    }

    /**
     * Constructor KeyValue
     *
     * @param doc
     * @param rsaKeyValue
     */
    public KeyValue(Document doc, RSAKeyValue rsaKeyValue) {
        super(doc);

        XMLUtils.addReturnToElement(this.constructionElement);
        this.constructionElement.appendChild(rsaKeyValue.getElement());
        XMLUtils.addReturnToElement(this.constructionElement);
    }

    /**
     * Constructor KeyValue
     *
     * @param doc
     * @param unknownKeyValue
     */
    public KeyValue(Document doc, Element unknownKeyValue) {
        super(doc);

        XMLUtils.addReturnToElement(this.constructionElement);
        this.constructionElement.appendChild(unknownKeyValue);
        XMLUtils.addReturnToElement(this.constructionElement);
    }

    /**
     * Constructor KeyValue
     *
     * @param doc
     * @param pk
     */
    public KeyValue(Document doc, PublicKey pk) {
        super(doc);

        XMLUtils.addReturnToElement(this.constructionElement);

        if (pk instanceof java.security.interfaces.DSAPublicKey) {
            DSAKeyValue dsa = new DSAKeyValue(this.doc, pk);

            this.constructionElement.appendChild(dsa.getElement());
            XMLUtils.addReturnToElement(this.constructionElement);
        } else if (pk instanceof java.security.interfaces.RSAPublicKey) {
            RSAKeyValue rsa = new RSAKeyValue(this.doc, pk);

            this.constructionElement.appendChild(rsa.getElement());
            XMLUtils.addReturnToElement(this.constructionElement);
        }
    }

    /**
     * Constructor KeyValue
     *
     * @param element
     * @param BaseURI
     * @throws XMLSecurityException
     */
    public KeyValue(Element element, String BaseURI) throws XMLSecurityException {
        super(element, BaseURI);
    }

    /**
     * Method getPublicKey
     *
     * @return the public key
     * @throws XMLSecurityException
     */
    public PublicKey getPublicKey() throws XMLSecurityException {
        Element rsa =
            XMLUtils.selectDsNode(
                this.constructionElement.getFirstChild(), Constants._TAG_RSAKEYVALUE, 0);

        if (rsa != null) {
            RSAKeyValue kv = new RSAKeyValue(rsa, this.baseURI);
            return kv.getPublicKey();
        }

        Element dsa =
            XMLUtils.selectDsNode(
                this.constructionElement.getFirstChild(), Constants._TAG_DSAKEYVALUE, 0);

        if (dsa != null) {
            DSAKeyValue kv = new DSAKeyValue(dsa, this.baseURI);
            return kv.getPublicKey();
        }

        return null;
    }

    /** @inheritDoc */
    public String getBaseLocalName() {
        return Constants._TAG_KEYVALUE;
    }
}
