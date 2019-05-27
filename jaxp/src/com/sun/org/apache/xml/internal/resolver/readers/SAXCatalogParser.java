package com.sun.org.apache.xml.internal.resolver.readers;

import com.sun.org.apache.xml.internal.resolver.Catalog;
import org.xml.sax.*;

/**
 * The SAXCatalogParser interface.
 *
 * <p>This interface must be implemented in order for a class to
 * participate as a parser for the SAXCatalogReader.
 */
public interface SAXCatalogParser extends ContentHandler, DocumentHandler {
    /** Set the Catalog for which parsing is being performed. */
    public void setCatalog(Catalog catalog);
}
