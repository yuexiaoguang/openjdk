package com.sun.org.apache.xerces.internal.util;

import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import org.w3c.dom.Node;

/**
 * <p>An <code>XMLInputSource</code> analogue to <code>javax.xml.transform.dom.DOMSource</code>.</p>
 */
public final class DOMInputSource extends XMLInputSource {

    private Node fNode;

    public DOMInputSource() {
        this(null);
    }

    public DOMInputSource(Node node) {
        super(null, getSystemIdFromNode(node), null);
        fNode = node;
    }

    public DOMInputSource(Node node, String systemId) {
        super(null, systemId, null);
        fNode = node;
    }

    public Node getNode() {
        return fNode;
    }

    public void setNode(Node node) {
        fNode = node;
    }

    private static String getSystemIdFromNode(Node node) {
        if (node != null) {
            try {
                return node.getBaseURI();
            }
            // If the DOM implementation is DOM Level 2
            // then a NoSuchMethodError will be thrown.
            // Just ignore it.
            catch (NoSuchMethodError e) {
                return null;
            }
            // There was a failure for some other reason
            // Ignore it as well.
            catch (Exception e) {
                return null;
            }
        }
        return null;
    }

} // DOMInputSource
