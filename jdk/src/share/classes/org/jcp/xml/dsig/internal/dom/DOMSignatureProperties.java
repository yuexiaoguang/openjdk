package org.jcp.xml.dsig.internal.dom;

import javax.xml.crypto.*;
import javax.xml.crypto.dom.DOMCryptoContext;
import javax.xml.crypto.dsig.*;

import java.util.*;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * DOM-based implementation of SignatureProperties.
 */
public final class DOMSignatureProperties extends DOMStructure
    implements SignatureProperties {

    private final String id;
    private final List<SignatureProperty> properties;

    /**
     * Creates a <code>DOMSignatureProperties</code> from the specified
     * parameters.
     *
     * @param properties a list of one or more {@link SignatureProperty}s. The
     *    list is defensively copied to protect against subsequent modification.
     * @param id the Id (may be <code>null</code>)
     * @return a <code>DOMSignatureProperties</code>
     * @throws ClassCastException if <code>properties</code> contains any
     *    entries that are not of type {@link SignatureProperty}
     * @throws IllegalArgumentException if <code>properties</code> is empty
     * @throws NullPointerException if <code>properties</code>
     */
    public DOMSignatureProperties(List<? extends SignatureProperty> properties,
                                  String id)
    {
        if (properties == null) {
            throw new NullPointerException("properties cannot be null");
        } else if (properties.isEmpty()) {
            throw new IllegalArgumentException("properties cannot be empty");
        } else {
            this.properties = Collections.unmodifiableList(
                new ArrayList<SignatureProperty>(properties));
            for (int i = 0, size = this.properties.size(); i < size; i++) {
                if (!(this.properties.get(i) instanceof SignatureProperty)) {
                    throw new ClassCastException
                        ("properties["+i+"] is not a valid type");
                }
            }
        }
        this.id = id;
    }

    /**
     * Creates a <code>DOMSignatureProperties</code> from an element.
     *
     * @param propsElem a SignatureProperties element
     * @throws MarshalException if a marshalling error occurs
     */
    public DOMSignatureProperties(Element propsElem, XMLCryptoContext context)
        throws MarshalException
    {
        // unmarshal attributes
        Attr attr = propsElem.getAttributeNodeNS(null, "Id");
        if (attr != null) {
            id = attr.getValue();
            propsElem.setIdAttributeNode(attr, true);
        } else {
            id = null;
        }

        NodeList nodes = propsElem.getChildNodes();
        int length = nodes.getLength();
        List<SignatureProperty> properties =
            new ArrayList<SignatureProperty>(length);
        for (int i = 0; i < length; i++) {
            Node child = nodes.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                String name = child.getLocalName();
                if (!name.equals("SignatureProperty")) {
                    throw new MarshalException("Invalid element name: " + name +
                                               ", expected SignatureProperty");
                }
                properties.add(new DOMSignatureProperty((Element)child,
                                                        context));
            }
        }
        if (properties.isEmpty()) {
            throw new MarshalException("properties cannot be empty");
        } else {
            this.properties = Collections.unmodifiableList(properties);
        }
    }

    public List getProperties() {
        return properties;
    }

    public String getId() {
        return id;
    }

    public void marshal(Node parent, String dsPrefix, DOMCryptoContext context)
        throws MarshalException
    {
        Document ownerDoc = DOMUtils.getOwnerDocument(parent);
        Element propsElem = DOMUtils.createElement(ownerDoc,
                                                   "SignatureProperties",
                                                   XMLSignature.XMLNS,
                                                   dsPrefix);

        // set attributes
        DOMUtils.setAttributeID(propsElem, "Id", id);

        // create and append any properties
        for (SignatureProperty property : properties) {
            ((DOMSignatureProperty)property).marshal(propsElem, dsPrefix,
                                                     context);
        }

        parent.appendChild(propsElem);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof SignatureProperties)) {
            return false;
        }
        SignatureProperties osp = (SignatureProperties)o;

        boolean idsEqual = (id == null ? osp.getId() == null
                                       : id.equals(osp.getId()));

        return (properties.equals(osp.getProperties()) && idsEqual);
    }

    @Override
    public int hashCode() {
        int result = 17;
        if (id != null) {
            result = 31 * result + id.hashCode();
        }
        result = 31 * result + properties.hashCode();

        return result;
    }
}
