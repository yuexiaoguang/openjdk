package com.sun.tools.internal.xjc.reader.dtd.bindinfo;

import javax.xml.parsers.ParserConfigurationException;

import com.sun.xml.internal.bind.marshaller.SAX2DOMEx;

import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;

final class DOMBuilder extends SAX2DOMEx {
    private Locator locator;

    public DOMBuilder(DocumentBuilderFactory f) throws ParserConfigurationException {
        super(f);
    }

    @Override
    public void setDocumentLocator(Locator locator) {
        super.setDocumentLocator(locator);
        this.locator = locator;
    }

    @Override
    public void startElement(String namespace, String localName, String qName, Attributes attrs) {
        super.startElement(namespace, localName, qName, attrs);
        DOMLocator.setLocationInfo(getCurrentElement(),locator);
    }
}
