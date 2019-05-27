package com.sun.xml.internal.org.jvnet.fastinfoset.sax;

import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public interface ExtendedContentHandler extends ContentHandler {

    /**
     * Receive notification of character data.
     *
     * @param ch the characters from the XML document
     * @param start the start position in the array
     * @param length the number of characters to read from the array
     * @param index true if the characters are indexed, otherwise false.
     * @throws org.xml.sax.SAXException any SAX exception, possibly
     *            wrapping another exception
     */
    public void characters(char[] ch, int start, int length, boolean index) throws SAXException;
}
