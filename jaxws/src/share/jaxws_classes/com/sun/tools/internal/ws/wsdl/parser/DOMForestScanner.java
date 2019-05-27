package com.sun.tools.internal.ws.wsdl.parser;

import com.sun.xml.internal.bind.unmarshaller.DOMScanner;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLFilterImpl;

/**
 * Produces a complete series of SAX events from any DOM node
 * in the DOMForest.
 *
 * <p>
 * This class hides a logic of re-associating {@link org.xml.sax.Locator}
 * to the generated SAX event stream.
 */
public class DOMForestScanner {

    private final DOMForest forest;

    /**
     * Scans DOM nodes of the given forest.
     *
     * DOM node parameters to the scan method must be a part of
     * this forest.
     */
    public DOMForestScanner( DOMForest _forest ) {
        this.forest = _forest;
    }

    /**
     * Generates the whole set of SAX events by treating
     * element e as if it's a root element.
     */
    public void scan( Element e, ContentHandler contentHandler ) throws SAXException {
        DOMScanner scanner = new DOMScanner();

        // insert the location resolver into the pipe line
        LocationResolver resolver = new LocationResolver(scanner);
        resolver.setContentHandler(contentHandler);

        // parse this DOM.
        scanner.setContentHandler(resolver);
        scanner.scan(e);
    }

    /**
     * Generates the whole set of SAX events from the given Document
     * in the DOMForest.
     */
    public void scan( Document d, ContentHandler contentHandler ) throws SAXException {
        scan( d.getDocumentElement(), contentHandler );
    }

    /**
     * Intercepts the invocation of the setDocumentLocator method
     * and passes itself as the locator.
     *
     * If the client calls one of the methods on the Locator interface,
     * use the LocatorTable to resolve the source location.
     */
    private class LocationResolver extends XMLFilterImpl implements Locator {
        LocationResolver( DOMScanner _parent ) {
            this.parent = _parent;
        }

        private final DOMScanner parent;

        /**
         * Flag that tells us whether we are processing a start element event
         * or an end element event.
         *
         * DOMScanner's getCurrentLocation method doesn't tell us which, but
         * this information is necessary to return the correct source line information.
         *
         * Thus we set this flag appropriately before we pass an event to
         * the next ContentHandler, thereby making it possible to figure
         * out which location to return.
         */
        private boolean inStart = false;

        public void setDocumentLocator(Locator locator) {
            // ignore one set by the parent.

            super.setDocumentLocator(this);
        }

        public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
            inStart = false;
            super.endElement(namespaceURI, localName, qName);
        }

        public void startElement(String namespaceURI, String localName, String qName, Attributes atts)
            throws SAXException {
            inStart = true;
            super.startElement(namespaceURI, localName, qName, atts);
        }




        private Locator findLocator() {
            Node n = parent.getCurrentLocation();
            if( n instanceof Element ) {
                Element e = (Element)n;
                if( inStart )
                    return forest.locatorTable.getStartLocation( e );
                else
                    return forest.locatorTable.getEndLocation( e );
            }
            return null;
        }

        //
        //
        // Locator methods
        //
        //
        public int getColumnNumber() {
            Locator l = findLocator();
            if(l!=null)     return l.getColumnNumber();
            return          -1;
        }

        public int getLineNumber() {
            Locator l = findLocator();
            if(l!=null)     return l.getLineNumber();
            return          -1;
        }

        public String getPublicId() {
            Locator l = findLocator();
            if(l!=null)     return l.getPublicId();
            return          null;
        }

        public String getSystemId() {
            Locator l = findLocator();
            if(l!=null)     return l.getSystemId();
            return          null;
        }

    }
}
