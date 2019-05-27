package com.sun.org.apache.xml.internal.security.keys.content;

import com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;
import com.sun.org.apache.xml.internal.security.utils.Constants;
import com.sun.org.apache.xml.internal.security.utils.SignatureElementProxy;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class KeyName extends SignatureElementProxy implements KeyInfoContent {

    /**
     * Constructor KeyName
     *
     * @param element
     * @param BaseURI
     * @throws XMLSecurityException
     */
    public KeyName(Element element, String BaseURI) throws XMLSecurityException {
        super(element, BaseURI);
    }

    /**
     * Constructor KeyName
     *
     * @param doc
     * @param keyName
     */
    public KeyName(Document doc, String keyName) {
        super(doc);

        this.addText(keyName);
    }

    /**
     * Method getKeyName
     *
     * @return key name
     */
    public String getKeyName() {
        return this.getTextFromTextChild();
    }

    /** @inheritDoc */
    public String getBaseLocalName() {
        return Constants._TAG_KEYNAME;
    }
}
