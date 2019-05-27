package com.sun.tools.internal.ws.wsdl.document.soap;

/**
 * A SOAP "use" enumeration.
 */
public final class SOAPUse {

    public static final SOAPUse LITERAL = new SOAPUse();
    public static final SOAPUse ENCODED = new SOAPUse();

    private SOAPUse() {
    }
}
