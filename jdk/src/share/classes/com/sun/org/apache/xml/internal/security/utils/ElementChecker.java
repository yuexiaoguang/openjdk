package com.sun.org.apache.xml.internal.security.utils;

import com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**@deprecated*/
@Deprecated
public interface ElementChecker {
    /**
     * Check that the element is the one expect
     *
     * @throws XMLSecurityException
     */
    void guaranteeThatElementInCorrectSpace(ElementProxy expected, Element actual)
        throws XMLSecurityException;

    boolean isNamespaceElement(Node el, String type, String ns);
}
