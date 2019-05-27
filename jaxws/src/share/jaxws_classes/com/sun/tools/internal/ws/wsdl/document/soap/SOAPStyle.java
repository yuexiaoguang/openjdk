package com.sun.tools.internal.ws.wsdl.document.soap;

/**
 * A SOAP "style" enumeration.
 */
public final class SOAPStyle {

    public static final SOAPStyle RPC = new SOAPStyle();
    public static final SOAPStyle DOCUMENT = new SOAPStyle();

    private SOAPStyle() {
    }
}
