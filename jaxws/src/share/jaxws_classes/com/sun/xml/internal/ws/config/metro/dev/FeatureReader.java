package com.sun.xml.internal.ws.config.metro.dev;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

/**
 * Parses a XML fragment and is expected to return a corresponding WebServiceFeature.
 */
public interface FeatureReader<T extends WebServiceFeature> {

    public static final QName ENABLED_ATTRIBUTE_NAME = new QName("enabled");

    /**
     * Parse an XML stream and return the corresponding WebServiceFeature instance.
     */
    public T parse(XMLEventReader reader) throws WebServiceException;

}
