package com.sun.org.apache.xml.internal.security.utils;


import com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * This is the base object for all objects which map directly to an Element from
 * the xenc spec.
 */
public abstract class EncryptionElementProxy extends ElementProxy {

    /**
     * Constructor EncryptionElementProxy
     *
     * @param doc
     */
    public EncryptionElementProxy(Document doc) {
        super(doc);
    }

    /**
     * Constructor EncryptionElementProxy
     *
     * @param element
     * @param BaseURI
     * @throws XMLSecurityException
     */
    public EncryptionElementProxy(Element element, String BaseURI)
        throws XMLSecurityException {
        super(element, BaseURI);
    }

    /** @inheritDoc */
    public final String getBaseNamespace() {
        return EncryptionConstants.EncryptionSpecNS;
    }
}
