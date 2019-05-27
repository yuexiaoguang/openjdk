package com.sun.org.apache.xml.internal.resolver.readers;

import com.sun.org.apache.xml.internal.resolver.Catalog;
import org.w3c.dom.Node;

/**
 * The DOMCatalogParser interface.
 *
 * <p>This interface must be implemented in order for a class to
 * participate as a parser for the DOMCatalogReader.
 */
public interface DOMCatalogParser {
    /**
     * Parse a DOM node as a catalog entry.
     *
     * <p>This method is expected to analyze the specified node and
     * construct appropriate catalog entry(ies) from it.</p>
     *
     * @param catalog The catalog for which this node is being considered.
     * @param node The DOM Node from the catalog.
     */
    public void parseCatalogEntry(Catalog catalog, Node node);
}
