package com.sun.tools.internal.xjc.reader;

import com.sun.xml.internal.bind.v2.WellKnownNamespace;


/**
 * Useful constant values.
 */
public class Const {

    /** XML namespace URI. */
    public final static String XMLNS_URI =
        "http://www.w3.org/2000/xmlns/";

    /** JAXB customization URI. */
    public final static String JAXB_NSURI =
        "http://java.sun.com/xml/ns/jaxb";

    /** XJC vendor extension namespace URI. */
    public final static String XJC_EXTENSION_URI =
        "http://java.sun.com/xml/ns/jaxb/xjc";

    /** RELAX NG namespace URI. */
    public static final String RELAXNG_URI =
        "http://relaxng.org/ns/structure/1.0";

    /** URI to represent DTD. */
    public static final String DTD = "DTD";

    /**
     * Attribute name of the expected media type.
     *
     * @see WellKnownNamespace#XML_MIME_URI
     * @see http://www.w3.org/TR/xml-media-types/
     */
    public static final String EXPECTED_CONTENT_TYPES = "expectedContentTypes";
}
