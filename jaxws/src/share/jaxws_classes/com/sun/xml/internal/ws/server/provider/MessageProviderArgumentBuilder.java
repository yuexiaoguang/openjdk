package com.sun.xml.internal.ws.server.provider;

import com.sun.xml.internal.ws.api.SOAPVersion;
import com.sun.xml.internal.ws.api.message.Message;
import com.sun.xml.internal.ws.api.message.Packet;
import com.sun.xml.internal.ws.fault.SOAPFaultBuilder;

final class MessageProviderArgumentBuilder extends ProviderArgumentsBuilder<Message> {
    private final SOAPVersion soapVersion;

    public MessageProviderArgumentBuilder(SOAPVersion soapVersion) {
        this.soapVersion = soapVersion;
    }

    @Override
    /*protected*/ public Message getParameter(Packet packet) {
        return packet.getMessage();
    }

    @Override
    protected Message getResponseMessage(Message returnValue) {
        return returnValue;
    }

    @Override
    protected Message getResponseMessage(Exception e) {
        return SOAPFaultBuilder.createSOAPFaultMessage(soapVersion, null, e);
    }
}
