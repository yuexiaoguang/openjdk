package com.sun.xml.internal.ws.handler;

import com.sun.xml.internal.ws.api.message.Message;
import com.sun.xml.internal.ws.api.message.Packet;
import com.sun.xml.internal.ws.api.WSBinding;
import com.sun.xml.internal.ws.spi.db.BindingContext;

import javax.xml.ws.LogicalMessage;
import javax.xml.ws.handler.LogicalMessageContext;

/**
 * Implementation of LogicalMessageContext. This class is used at runtime
 * to pass to the handlers for processing logical messages.
 *
 * <p>This Class delegates most of the fuctionality to Packet
 */
class LogicalMessageContextImpl extends MessageUpdatableContext implements LogicalMessageContext {
    private LogicalMessageImpl lm;
    private WSBinding binding;
//  private JAXBContext defaultJaxbContext;
    private BindingContext defaultJaxbContext;
    public LogicalMessageContextImpl(WSBinding binding, BindingContext defaultJAXBContext, Packet packet) {
        super(packet);
        this.binding = binding;
        this.defaultJaxbContext = defaultJAXBContext;
    }

    public LogicalMessage getMessage() {
        if(lm == null)
            lm = new LogicalMessageImpl(defaultJaxbContext, packet);
        return lm;
    }

    void setPacketMessage(Message newMessage){
        if(newMessage != null) {
            packet.setMessage(newMessage);
            lm = null;
        }
    }


    protected void updateMessage() {
        //If LogicalMessage is not acccessed, its not modified.
        if(lm != null) {
            //Check if LogicalMessageImpl has changed, if so construct new one
            // Packet are handled through MessageContext
            if(lm.isPayloadModifed()){
                Message msg = packet.getMessage();
                Message updatedMsg = lm.getMessage(msg.getHeaders(),msg.getAttachments(),binding);
                packet.setMessage(updatedMsg);
            }
            lm = null;
        }

    }

}
