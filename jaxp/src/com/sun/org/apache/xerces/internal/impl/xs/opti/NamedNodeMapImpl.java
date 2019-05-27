/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xerces.internal.impl.xs.opti;

import org.w3c.dom.Node;
import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;

import org.w3c.dom.DOMException;


public class NamedNodeMapImpl implements NamedNodeMap {

        Attr[] attrs;

        public NamedNodeMapImpl(Attr[] attrs) {
                this.attrs = attrs;
        }

        public Node getNamedItem(String name) {
                for (int i=0; i<attrs.length; i++) {
                        if (attrs[i].getName().equals(name)) {
                                return attrs[i];
                        }
                }
                return null;
        }

        public Node item(int index) {
                if (index < 0 && index > getLength()) {
                        return null;
                }
                return attrs[index];
        }

        public int getLength() {
                return attrs.length;
        }

        public Node getNamedItemNS(String namespaceURI, String localName) {
                for (int i=0; i<attrs.length; i++) {
                        if (attrs[i].getName().equals(localName) && attrs[i].getNamespaceURI().equals(namespaceURI)) {
                                return attrs[i];
                        }
                }
                return null;
        }

        public Node setNamedItemNS(Node arg) throws DOMException {
                throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
        }

        public Node setNamedItem(Node arg) throws DOMException {
                throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
        }

        public Node removeNamedItem(String name) throws DOMException {
                throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
        }

        public Node removeNamedItemNS(String namespaceURI, String localName) throws DOMException {
                throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
        }
}
