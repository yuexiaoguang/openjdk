package com.sun.xml.internal.messaging.saaj.soap;

import java.io.IOException;
import java.io.OutputStream;

import javax.xml.soap.SOAPEnvelope;
import javax.xml.transform.Source;

/**
 * Different implementations for SOAP Envelope must all implement this
 * interface.
 */
public interface Envelope extends SOAPEnvelope {
    /**
     * Get the content as a JAXP Source.
     */
    Source getContent();

    /**
     * Output the content.
     */
    void output(OutputStream out) throws IOException;

    /**
     * Output the content.
     */
    void output(OutputStream out, boolean isFastInfoset) throws IOException;
}
