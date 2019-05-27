package javax.xml.crypto.dom;

import javax.xml.crypto.URIReference;
import org.w3c.dom.Node;

/**
 * A DOM-specific {@link URIReference}. The purpose of this class is to
 * provide additional context necessary for resolving XPointer URIs or
 * same-document references.
 */
public interface DOMURIReference extends URIReference {

    /**
     * Returns the here node.
     *
     * @return the attribute or processing instruction node or the
     *    parent element of the text node that directly contains the URI
     */
    Node getHere();
}
