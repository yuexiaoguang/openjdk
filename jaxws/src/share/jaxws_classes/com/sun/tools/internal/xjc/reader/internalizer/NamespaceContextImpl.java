package com.sun.tools.internal.xjc.reader.internalizer;

import java.util.Iterator;
import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * Implements {@link NamespaceContext} by looking at the in-scope
 * namespace binding of a DOM element.
 */
final class NamespaceContextImpl implements NamespaceContext {
    private final Element e;

    public NamespaceContextImpl(Element e) {
        this.e = e;
    }

    public String getNamespaceURI(String prefix) {
        Node parent = e;
        String namespace = null;
        final String prefixColon = prefix + ':';

        if (prefix.equals("xml")) {
            namespace = XMLConstants.XML_NS_URI;
        } else {
            int type;

            while ((null != parent) && (null == namespace)
                    && (((type = parent.getNodeType()) == Node.ELEMENT_NODE)
                    || (type == Node.ENTITY_REFERENCE_NODE))) {
                if (type == Node.ELEMENT_NODE) {
                    if (parent.getNodeName().startsWith(prefixColon))
                        return parent.getNamespaceURI();
                    NamedNodeMap nnm = parent.getAttributes();

                    for (int i = 0; i < nnm.getLength(); i++) {
                        Node attr = nnm.item(i);
                        String aname = attr.getNodeName();
                        boolean isPrefix = aname.startsWith("xmlns:");

                        if (isPrefix || aname.equals("xmlns")) {
                            int index = aname.indexOf(':');
                            String p = isPrefix ? aname.substring(index + 1) : "";

                            if (p.equals(prefix)) {
                                namespace = attr.getNodeValue();

                                break;
                            }
                        }
                    }
                }

                parent = parent.getParentNode();
            }
        }

        if(prefix.equals(""))
            return "";  // default namespace
        return namespace;
    }

    public String getPrefix(String namespaceURI) {
        throw new UnsupportedOperationException();
    }

    public Iterator getPrefixes(String namespaceURI) {
        throw new UnsupportedOperationException();
    }
}
