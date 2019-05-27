package com.sun.org.apache.xml.internal.security.utils;


/**
 * A Factory to return a JDKXPathAPI instance.
 */
public class JDKXPathFactory extends XPathFactory {

    /**
     * Get a new XPathAPI instance
     */
    public XPathAPI newXPathAPI() {
        return new JDKXPathAPI();
    }
}
