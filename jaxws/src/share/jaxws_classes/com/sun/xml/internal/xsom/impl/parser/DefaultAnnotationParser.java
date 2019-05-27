package com.sun.xml.internal.xsom.impl.parser;

import com.sun.xml.internal.xsom.parser.AnnotationContext;
import com.sun.xml.internal.xsom.parser.AnnotationParser;
import org.xml.sax.ContentHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.helpers.DefaultHandler;

/**
 * AnnotationParser that just ignores annotation.
 *
 * <p>
 * This class doesn't have any state. So it should be used as a singleton.
 */
class DefaultAnnotationParser extends AnnotationParser {

    private DefaultAnnotationParser() {}

    public static final AnnotationParser theInstance = new DefaultAnnotationParser();

    public ContentHandler getContentHandler(
        AnnotationContext contest, String elementName,
        ErrorHandler errorHandler, EntityResolver entityResolver ) {
        return new DefaultHandler();
    }

    public Object getResult( Object existing ) {
        return null;
    }


}
