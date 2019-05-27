package com.sun.tools.internal.ws.processor.model.exporter;

import org.xml.sax.ContentHandler;

/**
 * "Opaque" object in the object graph that knows how
 * to persist itself to XML.
 *
 * TODO: ExternalObjectReader
 */
public interface ExternalObject {
    /**
     * Type name of this object. This will be used
     * when loading the object back from XML.
     */
    String getType();

    /**
     * Saves the object into XML.
     */
    void saveTo(ContentHandler receiver);
}
