package com.sun.org.apache.xerces.internal.xni.parser;

import com.sun.org.apache.xerces.internal.xni.XMLDTDHandler;

/**
 * Defines a DTD source. In other words, any object that implements
 * this interface is able to emit DTD "events" to the registered
 * DTD handler. These events could be produced by parsing an XML
 * document's internal or external subset, could be generated from
 * some other source, or could be created programmatically. This
 * interface does not say <em>how</em> the events are created, only
 * that the implementor is able to emit them.
 */
public interface XMLDTDSource {

    //
    // XMLDTDSource methods
    //

    /** Sets the DTD handler. */
    public void setDTDHandler(XMLDTDHandler handler);

    /** Returns the DTD handler. */
    public XMLDTDHandler getDTDHandler();

} // interface XMLDTDSource
