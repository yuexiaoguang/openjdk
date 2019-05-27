package com.sun.org.apache.xml.internal.security.signature;

import com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;
import com.sun.org.apache.xml.internal.security.utils.Constants;
import com.sun.org.apache.xml.internal.security.utils.SignatureElementProxy;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


/**
 * Handles <code>&lt;ds:Object&gt;</code> elements
 * <code>Object<code> {@link Element} supply facility which can contain any kind data
 */
public class ObjectContainer extends SignatureElementProxy {

    /**
     * Constructs {@link ObjectContainer}
     *
     * @param doc the {@link Document} in which <code>Object</code> element is placed
     */
    public ObjectContainer(Document doc) {
        super(doc);
    }

    /**
     * Constructs {@link ObjectContainer} from {@link Element}
     *
     * @param element is <code>Object</code> element
     * @param baseURI the URI of the resource where the XML instance was stored
     * @throws XMLSecurityException
     */
    public ObjectContainer(Element element, String baseURI) throws XMLSecurityException {
        super(element, baseURI);
    }

    /**
     * Sets the <code>Id</code> attribute
     *
     * @param Id <code>Id</code> attribute
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
     * Sets the <code>MimeType</code> attribute
     *
     * @param MimeType the <code>MimeType</code> attribute
     */
    public void setMimeType(String MimeType) {
        if (MimeType != null) {
            this.constructionElement.setAttributeNS(null, Constants._ATT_MIMETYPE, MimeType);
        }
    }

    /**
     * Returns the <code>MimeType</code> attribute
     *
     * @return the <code>MimeType</code> attribute
     */
    public String getMimeType() {
        return this.constructionElement.getAttributeNS(null, Constants._ATT_MIMETYPE);
    }

    /**
     * Sets the <code>Encoding</code> attribute
     *
     * @param Encoding the <code>Encoding</code> attribute
     */
    public void setEncoding(String Encoding) {
        if (Encoding != null) {
            this.constructionElement.setAttributeNS(null, Constants._ATT_ENCODING, Encoding);
        }
    }

    /**
     * Returns the <code>Encoding</code> attribute
     *
     * @return the <code>Encoding</code> attribute
     */
    public String getEncoding() {
        return this.constructionElement.getAttributeNS(null, Constants._ATT_ENCODING);
    }

    /**
     * Adds child Node
     *
     * @param node child Node
     * @return the new node in the tree.
     */
    public Node appendChild(Node node) {
        return this.constructionElement.appendChild(node);
    }

    /** @inheritDoc */
    public String getBaseLocalName() {
        return Constants._TAG_OBJECT;
    }
}
