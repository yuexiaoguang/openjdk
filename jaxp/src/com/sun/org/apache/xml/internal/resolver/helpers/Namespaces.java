package com.sun.org.apache.xml.internal.resolver.helpers;

import org.w3c.dom.*;

/**
 * Static Namespace query methods.
 *
 * <p>This class defines a set of static methods that can be called
 * to analyze the namespace properties of DOM nodes.</p>
 */
public class Namespaces {
    /**
     * Returns the "prefix" part of a QName or the empty string (not
     * null) if the name has no prefix.
     *
     * @param element The QName of an element.
     * @return The prefix part of the element name.
     */
    public static String getPrefix(Element element) {
        String name = element.getTagName();
        String prefix = "";

        if (name.indexOf(':') > 0) {
            prefix = name.substring(0, name.indexOf(':'));
        }

        return prefix;
    }

    /**
     * Returns the "localname" part of a QName, which is the whole
     * name if it has no prefix.
     *
     * @param element The QName of an element.
     * @return The local part of a QName.
     */
    public static String getLocalName(Element element) {
        String name = element.getTagName();

        if (name.indexOf(':') > 0) {
            name = name.substring(name.indexOf(':')+1);
        }

        return name;
    }

    /**
     * Returns the namespace URI for the specified prefix at the
     * specified context node.
     *
     * @param node The context node.
     * @param prefix The prefix.
     * @return The namespace URI associated with the prefix, or
     * null if no namespace declaration exists for the prefix.
     */
    public static String getNamespaceURI(Node node, String prefix) {
        if (node == null || node.getNodeType() != Node.ELEMENT_NODE) {
            return null;
        }

        if (prefix.equals("")) {
            if (((Element) node).hasAttribute("xmlns")) {
                return ((Element) node).getAttribute("xmlns");
            }
        } else {
            String nsattr = "xmlns:" + prefix;
            if (((Element) node).hasAttribute(nsattr)) {
                return ((Element) node).getAttribute(nsattr);
            }
        }

        return getNamespaceURI(node.getParentNode(), prefix);
    }

    /**
     * Returns the namespace URI for the namespace to which the
     * element belongs.
     *
     * @param element The element.
     * @return The namespace URI associated with the namespace of the
     * element, or null if no namespace declaration exists for it.
     */
    public static String getNamespaceURI(Element element) {
        String prefix = getPrefix(element);
        return getNamespaceURI(element, prefix);
    }
}
