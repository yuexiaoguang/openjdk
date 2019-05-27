package com.sun.xml.internal.rngom.xml.sax;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;

/**
 * {@link XMLReaderCreator} that uses JAXP to create
 * {@link XMLReader}s.
 */
public class JAXPXMLReaderCreator implements XMLReaderCreator {

    private final SAXParserFactory spf;

    public JAXPXMLReaderCreator( SAXParserFactory spf ) {
        this.spf = spf;
    }

    /**
     * Creates a {@link JAXPXMLReaderCreator} by using
     * {@link SAXParserFactory#newInstance()}.
     */
    public JAXPXMLReaderCreator() {
        spf = SAXParserFactory.newInstance();
        try {
            spf.setNamespaceAware(true);
            spf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(JAXPXMLReaderCreator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXNotRecognizedException ex) {
            Logger.getLogger(JAXPXMLReaderCreator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXNotSupportedException ex) {
            Logger.getLogger(JAXPXMLReaderCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @see com.sun.xml.internal.rngom.xml.sax.XMLReaderCreator#createXMLReader()
     */
    public XMLReader createXMLReader() throws SAXException {
        try {
            return spf.newSAXParser().getXMLReader();
        } catch (ParserConfigurationException e) {
            throw new SAXException(e);
        }
    }

}
