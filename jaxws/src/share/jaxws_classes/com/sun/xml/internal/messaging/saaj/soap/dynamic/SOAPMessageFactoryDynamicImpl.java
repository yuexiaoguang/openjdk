package com.sun.xml.internal.messaging.saaj.soap.dynamic;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.soap.*;

import com.sun.xml.internal.messaging.saaj.SOAPExceptionImpl;
import com.sun.xml.internal.messaging.saaj.soap.MessageFactoryImpl;

public class SOAPMessageFactoryDynamicImpl extends MessageFactoryImpl {
    public SOAPMessage createMessage() throws SOAPException {
        throw new UnsupportedOperationException(
                "createMessage() not supported for Dynamic Protocol");
    }
}
