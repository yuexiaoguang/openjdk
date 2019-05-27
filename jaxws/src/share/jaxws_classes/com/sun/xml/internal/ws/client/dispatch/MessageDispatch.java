package com.sun.xml.internal.ws.client.dispatch;

import com.sun.xml.internal.ws.api.addressing.WSEndpointReference;
import com.sun.xml.internal.ws.api.message.Message;
import com.sun.xml.internal.ws.api.message.Packet;
import com.sun.xml.internal.ws.api.pipe.Tube;
import com.sun.xml.internal.ws.api.client.WSPortInfo;
import com.sun.xml.internal.ws.binding.BindingImpl;
import com.sun.xml.internal.ws.client.WSServiceDelegate;
import com.sun.xml.internal.ws.client.PortInfo;

import javax.xml.namespace.QName;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service.Mode;

/**
 * {@link Dispatch} implementation for {@link Message}.
 */
public class MessageDispatch extends DispatchImpl<Message> {
    @Deprecated
    public MessageDispatch(QName port, WSServiceDelegate service, Tube pipe, BindingImpl binding, WSEndpointReference epr) {
        super(port, Mode.MESSAGE, service, pipe, binding, epr);
    }

    public MessageDispatch(WSPortInfo portInfo, BindingImpl binding, WSEndpointReference epr) {
            super(portInfo, Mode.MESSAGE, binding, epr, true);
    }

    @Override
    Message toReturnValue(Packet response) {
        return response.getMessage();
    }

    @Override
    Packet createPacket(Message msg) {
        return new Packet(msg);
    }
}
