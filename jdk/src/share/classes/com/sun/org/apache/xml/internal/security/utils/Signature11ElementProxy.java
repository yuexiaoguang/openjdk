package com.sun.org.apache.xml.internal.security.utils;

import com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Class SignatureElementProxy
 */
public abstract class Signature11ElementProxy extends ElementProxy {

    protected Signature11ElementProxy() {
    };

    /**
     * Constructor Signature11ElementProxy
     *
     * @param doc
     */
    public Signature11ElementProxy(Document doc) {
        if (doc == null) {
            throw new RuntimeException("Document is null");
        }

        this.doc = doc;
        this.constructionElement =
            XMLUtils.createElementInSignature11Space(this.doc, this.getBaseLocalName());
    }

    /**
     * Constructor Signature11ElementProxy
     *
     * @param element
     * @param BaseURI
     * @throws XMLSecurityException
     */
    public Signature11ElementProxy(Element element, String BaseURI) throws XMLSecurityException {
        super(element, BaseURI);

    }

    /** @inheritDoc */
    public String getBaseNamespace() {
        return Constants.SignatureSpec11NS;
    }
}
