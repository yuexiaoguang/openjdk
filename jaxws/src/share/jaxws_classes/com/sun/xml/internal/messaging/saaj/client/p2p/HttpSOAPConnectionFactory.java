package com.sun.xml.internal.messaging.saaj.client.p2p;

import javax.xml.soap.*;

/**
 * Implementation of the SOAPConnectionFactory
 */
public class HttpSOAPConnectionFactory extends SOAPConnectionFactory {

    public SOAPConnection createConnection()
        throws SOAPException
    {
        return new HttpSOAPConnection();
    }
}
