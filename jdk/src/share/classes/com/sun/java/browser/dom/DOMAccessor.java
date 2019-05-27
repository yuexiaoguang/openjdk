package com.sun.java.browser.dom;


public interface DOMAccessor
{
    /**
     * Returns the Document object of the DOM.
     */
    public org.w3c.dom.Document getDocument(Object obj) throws org.w3c.dom.DOMException;

    /**
     * Returns a DOMImplementation object.
     */
    public org.w3c.dom.DOMImplementation getDOMImplementation();
}
