package com.sun.xml.internal.messaging.saaj.soap.ver1_2;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.soap.*;

import com.sun.xml.internal.messaging.saaj.SOAPExceptionImpl;
import com.sun.xml.internal.messaging.saaj.soap.MessageFactoryImpl;
import com.sun.xml.internal.messaging.saaj.soap.MessageImpl;

public class SOAPMessageFactory1_2Impl extends MessageFactoryImpl {

    public SOAPMessage createMessage() throws SOAPException {
        return new Message1_2Impl();
    }

    public SOAPMessage createMessage(boolean isFastInfoset,
        boolean acceptFastInfoset) throws SOAPException
    {
        return new Message1_2Impl(isFastInfoset, acceptFastInfoset);
    }

    public SOAPMessage createMessage(MimeHeaders headers, InputStream in) throws IOException, SOAPExceptionImpl {

        if (headers == null) {
            headers = new MimeHeaders();
        }

        if (getContentType(headers) == null) {
            headers.setHeader("Content-Type", SOAPConstants.SOAP_1_2_CONTENT_TYPE);
        }

        MessageImpl msg = new Message1_2Impl(headers, in);
        msg.setLazyAttachments(lazyAttachments);
        return msg;
    }
}
