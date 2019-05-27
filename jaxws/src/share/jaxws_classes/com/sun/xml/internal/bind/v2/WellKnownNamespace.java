package com.sun.xml.internal.bind.v2;

import javax.xml.XMLConstants;

/**
 * Well-known namespace URIs.
 */
public abstract class WellKnownNamespace {
    private WellKnownNamespace() {} // no instanciation please

    /**
     * @deprecated Use javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI instead;
     * @return
     * @throws CloneNotSupportedException
     */
    @Deprecated()
    public static final String XML_SCHEMA = XMLConstants.W3C_XML_SCHEMA_NS_URI;

    /**
     * @deprecated Use javax.xml.XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI instead
     * @return
     * @throws CloneNotSupportedException
     */
    @Deprecated()
    public static final String XML_SCHEMA_INSTANCE = XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI;

    /**
     * @deprecated Use javax.xml.XMLConstants.XML_NS_URI instead;
     * @return
     * @throws CloneNotSupportedException
     */
    @Deprecated()
    public static final String XML_NAMESPACE_URI = XMLConstants.XML_NS_URI;

    public static final String XOP = "http://www.w3.org/2004/08/xop/include";

    public static final String SWA_URI = "http://ws-i.org/profiles/basic/1.1/xsd";

    public static final String XML_MIME_URI = "http://www.w3.org/2005/05/xmlmime";

    public static final String JAXB = "http://java.sun.com/xml/ns/jaxb";

}
