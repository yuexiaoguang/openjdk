package com.sun.tools.internal.ws.wsdl.framework;

import org.xml.sax.Locator;

import javax.xml.namespace.QName;

/**
 * Interface implemented by classes that are mappable to XML elements.
 */
public interface Elemental {
    public QName getElementName();
    public Locator getLocator();
}
