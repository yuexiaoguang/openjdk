package org.jcp.xml.dsig.internal.dom;

import javax.xml.crypto.*;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.crypto.dsig.spec.XPathFilterParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

/**
 * DOM-based implementation of XPath Filtering Transform.
 * (Uses Apache XML-Sec Transform implementation)
 */
public final class DOMXPathTransform extends ApacheTransform {

    public void init(TransformParameterSpec params)
        throws InvalidAlgorithmParameterException
    {
        if (params == null) {
            throw new InvalidAlgorithmParameterException("params are required");
        } else if (!(params instanceof XPathFilterParameterSpec)) {
            throw new InvalidAlgorithmParameterException
                ("params must be of type XPathFilterParameterSpec");
        }
        this.params = params;
    }

    public void init(XMLStructure parent, XMLCryptoContext context)
        throws InvalidAlgorithmParameterException
    {
        super.init(parent, context);
        unmarshalParams(DOMUtils.getFirstChildElement(transformElem));
    }

    private void unmarshalParams(Element paramsElem) {
        String xPath = paramsElem.getFirstChild().getNodeValue();
        // create a Map of namespace prefixes
        NamedNodeMap attributes = paramsElem.getAttributes();
        if (attributes != null) {
            int length = attributes.getLength();
            Map<String, String> namespaceMap =
                new HashMap<String, String>(length);
            for (int i = 0; i < length; i++) {
                Attr attr = (Attr)attributes.item(i);
                String prefix = attr.getPrefix();
                if (prefix != null && prefix.equals("xmlns")) {
                    namespaceMap.put(attr.getLocalName(), attr.getValue());
                }
            }
            this.params = new XPathFilterParameterSpec(xPath, namespaceMap);
        } else {
            this.params = new XPathFilterParameterSpec(xPath);
        }
    }

    public void marshalParams(XMLStructure parent, XMLCryptoContext context)
        throws MarshalException
    {
        super.marshalParams(parent, context);
        XPathFilterParameterSpec xp =
            (XPathFilterParameterSpec)getParameterSpec();
        Element xpathElem = DOMUtils.createElement(ownerDoc, "XPath",
             XMLSignature.XMLNS, DOMUtils.getSignaturePrefix(context));
        xpathElem.appendChild(ownerDoc.createTextNode(xp.getXPath()));

        // add namespace attributes, if necessary
        @SuppressWarnings("unchecked")
        Set<Map.Entry<String, String>> entries =
            xp.getNamespaceMap().entrySet();
        for (Map.Entry<String, String> entry : entries) {
            xpathElem.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:" +
                                     entry.getKey(),
                                     entry.getValue());
        }

        transformElem.appendChild(xpathElem);
    }
}
