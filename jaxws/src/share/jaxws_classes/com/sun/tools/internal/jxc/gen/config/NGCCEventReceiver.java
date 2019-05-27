package com.sun.tools.internal.jxc.gen.config;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * <p><b>
 *     Auto-generated, do not edit.
 * </b></p>
 */
public interface NGCCEventReceiver {
    void enterElement(String uri, String localName, String qname,Attributes atts) throws SAXException;
    void leaveElement(String uri, String localName, String qname) throws SAXException;
    void text(String value) throws SAXException;
    void enterAttribute(String uri, String localName, String qname) throws SAXException;
    void leaveAttribute(String uri, String localName, String qname) throws SAXException;
}
