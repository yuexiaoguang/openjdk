package com.sun.tools.internal.xjc;

import java.net.URL;

import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.ValidatorHandler;

import com.sun.xml.internal.bind.v2.util.XmlFactory;
import javax.xml.XMLConstants;
import org.xml.sax.SAXException;

import static com.sun.xml.internal.bind.v2.util.XmlFactory.allowExternalAccess;

/**
 * Wraps a JAXP {@link Schema} object and lazily instantiate it.
 *
 * This object is thread-safe. There should be only one instance of
 * this for the whole VM.
 */
public final class SchemaCache {

    private Schema schema;

    private final URL source;

    public SchemaCache(URL source) {
        this.source = source;
    }

    public ValidatorHandler newValidator() {
        synchronized(this) {
            if(schema==null) {
                try {
                    // do not disable secure processing - these are well-known schemas
                    SchemaFactory sf = XmlFactory.createSchemaFactory(XMLConstants.W3C_XML_SCHEMA_NS_URI, false);
                    schema = allowExternalAccess(sf, "file", false).newSchema(source);
                } catch (SAXException e) {
                    // we make sure that the schema is correct before we ship.
                    throw new AssertionError(e);
                }
            }
        }

        ValidatorHandler handler = schema.newValidatorHandler();
        return handler;
    }

}
