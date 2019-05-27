package com.sun.xml.internal.ws.api.pipe;

/**
 * A Content-Type transport header that will be returned by {@link Codec#encode(com.sun.xml.internal.ws.api.message.Packet, java.io.OutputStream)}.
 * It will provide the Content-Type header and also take care of SOAP 1.1 SOAPAction header.
 */
public interface ContentType extends com.oracle.webservices.internal.api.message.ContentType {
}
