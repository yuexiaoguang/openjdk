package com.sun.xml.internal.ws.handler;

import com.sun.xml.internal.ws.api.WSBinding;
import com.sun.xml.internal.ws.api.message.Messages;
import java.util.List;
import javax.xml.ws.ProtocolException;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.http.HTTPException;

/**
 * This is used only for XML/HTTP binding
 */
final class XMLHandlerProcessor<C extends MessageUpdatableContext> extends HandlerProcessor<C> {

    /**
     * Creates a new instance of LogicalHandlerProcessor
     */
    public XMLHandlerProcessor(HandlerTube owner, WSBinding binding, List<? extends Handler> chain) {
        super(owner, binding, chain);
    }

    /*
     * TODO: This is valid only for XML/HTTP binding
     * Empty the XML message
     */
    final void insertFaultMessage(C context,
            ProtocolException exception) {
        if(exception instanceof HTTPException) {
            context.put(MessageContext.HTTP_RESPONSE_CODE,((HTTPException)exception).getStatusCode());
        }
        if (context != null) {
            // non-soap case
            context.setPacketMessage(Messages.createEmpty(binding.getSOAPVersion()));
        }
    }
}
