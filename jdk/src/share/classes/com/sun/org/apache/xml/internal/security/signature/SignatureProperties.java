package com.sun.org.apache.xml.internal.security.signature;

import com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;
import com.sun.org.apache.xml.internal.security.utils.Constants;
import com.sun.org.apache.xml.internal.security.utils.SignatureElementProxy;
import com.sun.org.apache.xml.internal.security.utils.XMLUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Handles <code>&lt;ds:SignatureProperties&gt;</code> elements
 * This Element holds {@link SignatureProperty} that contian additional information items
 * concerning the generation of the signature.
 * for example, data-time stamp, serial number of cryptographic hardware.
 */
public class SignatureProperties extends SignatureElementProxy {

    /**
     * Constructor SignatureProperties
     *
     * @param doc
     */
    public SignatureProperties(Document doc) {
        super(doc);

        XMLUtils.addReturnToElement(this.constructionElement);
    }

    /**
     * Constructs {@link SignatureProperties} from {@link Element}
     * @param element <code>SignatureProperties</code> element
     * @param BaseURI the URI of the resource where the XML instance was stored
     * @throws XMLSecurityException
     */
    public SignatureProperties(Element element, String BaseURI) throws XMLSecurityException {
        super(element, BaseURI);

        Attr attr = element.getAttributeNodeNS(null, "Id");
        if (attr != null) {
            element.setIdAttributeNode(attr, true);
        }

        int length = getLength();
        for (int i = 0; i < length; i++) {
            Element propertyElem =
                XMLUtils.selectDsNode(this.constructionElement, Constants._TAG_SIGNATUREPROPERTY, i);
            Attr propertyAttr = propertyElem.getAttributeNodeNS(null, "Id");
            if (propertyAttr != null) {
                propertyElem.setIdAttributeNode(propertyAttr, true);
            }
        }
    }

    /**
     * Return the nonnegative number of added SignatureProperty elements.
     *
     * @return the number of SignatureProperty elements
     */
    public int getLength() {
        Element[] propertyElems =
            XMLUtils.selectDsNodes(this.constructionElement, Constants._TAG_SIGNATUREPROPERTY);

        return propertyElems.length;
    }

    /**
     * Return the <it>i</it><sup>th</sup> SignatureProperty. Valid <code>i</code>
     * values are 0 to <code>{link@ getSize}-1</code>.
     *
     * @param i Index of the requested {@link SignatureProperty}
     * @return the <it>i</it><sup>th</sup> SignatureProperty
     * @throws XMLSignatureException
     */
    public SignatureProperty item(int i) throws XMLSignatureException {
        try {
            Element propertyElem =
                XMLUtils.selectDsNode(this.constructionElement, Constants._TAG_SIGNATUREPROPERTY, i);

            if (propertyElem == null) {
                return null;
            }
            return new SignatureProperty(propertyElem, this.baseURI);
        } catch (XMLSecurityException ex) {
            throw new XMLSignatureException("empty", ex);
        }
    }

    /**
     * Sets the <code>Id</code> attribute
     *
     * @param Id the <code>Id</code> attribute
     */
    public void setId(String Id) {
        if (Id != null) {
            this.constructionElement.setAttributeNS(null, Constants._ATT_ID, Id);
            this.constructionElement.setIdAttributeNS(null, Constants._ATT_ID, true);
        }
    }

    /**
     * Returns the <code>Id</code> attribute
     *
     * @return the <code>Id</code> attribute
     */
    public String getId() {
        return this.constructionElement.getAttributeNS(null, Constants._ATT_ID);
    }

    /**
     * Method addSignatureProperty
     *
     * @param sp
     */
    public void addSignatureProperty(SignatureProperty sp) {
        this.constructionElement.appendChild(sp.getElement());
        XMLUtils.addReturnToElement(this.constructionElement);
    }

    /** @inheritDoc */
    public String getBaseLocalName() {
        return Constants._TAG_SIGNATUREPROPERTIES;
    }
}
