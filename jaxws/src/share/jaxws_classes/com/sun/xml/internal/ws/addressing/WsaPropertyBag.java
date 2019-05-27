package com.sun.xml.internal.ws.addressing;

import com.oracle.webservices.internal.api.message.BasePropertySet;
import com.sun.istack.internal.NotNull;
import com.sun.xml.internal.ws.api.SOAPVersion;
import com.sun.xml.internal.ws.api.addressing.AddressingVersion;
import com.sun.xml.internal.ws.api.addressing.WSEndpointReference;
import com.sun.xml.internal.ws.api.message.AddressingUtils;
import com.sun.xml.internal.ws.api.message.Header;
import com.sun.xml.internal.ws.api.message.Message;
import com.sun.xml.internal.ws.api.message.Packet;
import com.sun.xml.internal.ws.developer.JAXWSProperties;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;


/**
 * Provides access to the Addressing headers.
 */
public class WsaPropertyBag extends BasePropertySet {

    public static final String WSA_REPLYTO_FROM_REQUEST = "com.sun.xml.internal.ws.addressing.WsaPropertyBag.ReplyToFromRequest";
    public static final String WSA_FAULTTO_FROM_REQUEST = "com.sun.xml.internal.ws.addressing.WsaPropertyBag.FaultToFromRequest";
    public static final String WSA_MSGID_FROM_REQUEST = "com.sun.xml.internal.ws.addressing.WsaPropertyBag.MessageIdFromRequest";
    public static final String WSA_TO = "com.sun.xml.internal.ws.addressing.WsaPropertyBag.To";

    private final @NotNull AddressingVersion addressingVersion;
    private final @NotNull SOAPVersion soapVersion;
    /**
     * We can't store {@link Message} here as those may get replaced as
     * the packet travels through the pipeline.
     */
    private final @NotNull Packet packet;

    public WsaPropertyBag(AddressingVersion addressingVersion, SOAPVersion soapVersion, Packet packet) {
        this.addressingVersion = addressingVersion;
        this.soapVersion = soapVersion;
        this.packet = packet;
    }

    /**
     * Gets the <tt>wsa:To</tt> header.
     *
     * @return
     *      null if the incoming SOAP message didn't have the header.
     */
    @Property(JAXWSProperties.ADDRESSING_TO)
    public String getTo() throws XMLStreamException {
      if (packet.getMessage() == null) {
        return null;
      }
      Header h = packet.getMessage().getHeaders().get(addressingVersion.toTag, false);
      if(h==null) return null;
      return h.getStringContent();
    }

    /**
     * Gets the <tt>wsa:To</tt> header.
     *
     * @return
     *      null if the incoming SOAP message didn't have the header.
     */
    @Property(WSA_TO)
    public WSEndpointReference getToAsReference() throws XMLStreamException {
      if (packet.getMessage() == null) {
        return null;
      }
      Header h = packet.getMessage().getHeaders().get(addressingVersion.toTag, false);
      if(h==null) return null;
      return new WSEndpointReference(h.getStringContent(),addressingVersion);
    }

    /**
     * Gets the <tt>wsa:From</tt> header.
     *
     * @return
     *      null if the incoming SOAP message didn't have the header.
     */
    @Property(JAXWSProperties.ADDRESSING_FROM)
    public WSEndpointReference getFrom() throws XMLStreamException {
        return getEPR(addressingVersion.fromTag);
    }

    /**
     * Gets the <tt>wsa:Action</tt> header content as String.
     *
     * @return
     *      null if the incoming SOAP message didn't have the header.
     */
    @Property(JAXWSProperties.ADDRESSING_ACTION)
    public String getAction() {
        if (packet.getMessage() == null) {
          return null;
        }
        Header h = packet.getMessage().getHeaders().get(addressingVersion.actionTag, false);
        if(h==null) return null;
        return h.getStringContent();
    }

    /**
     * Gets the <tt>wsa:MessageID</tt> header content as String.
     *
     * @return
     *      null if the incoming SOAP message didn't have the header.
     */
    // WsaServerTube.REQUEST_MESSAGE_ID is exposed for backward compatibility with 2.1
    @Property({JAXWSProperties.ADDRESSING_MESSAGEID,WsaServerTube.REQUEST_MESSAGE_ID})
    public String getMessageID() {
        if (packet.getMessage() == null) {
          return null;
        }
        return AddressingUtils.getMessageID(packet.getMessage().getHeaders(), addressingVersion,soapVersion);
    }

    private WSEndpointReference getEPR(QName tag) throws XMLStreamException {
        if (packet.getMessage() == null) {
          return null;
        }
        Header h = packet.getMessage().getHeaders().get(tag, false);
        if(h==null) return null;
        return h.readAsEPR(addressingVersion);
    }

    protected PropertyMap getPropertyMap() {
        return model;
    }

    private static final PropertyMap model;
    static {
        model = parse(WsaPropertyBag.class);
    }

    private WSEndpointReference _replyToFromRequest = null;

    @Property(WSA_REPLYTO_FROM_REQUEST)
    public WSEndpointReference getReplyToFromRequest() {
      return _replyToFromRequest;
    }

    public void setReplyToFromRequest(WSEndpointReference ref) {
      _replyToFromRequest = ref;
    }

    private WSEndpointReference _faultToFromRequest = null;

    @Property(WSA_FAULTTO_FROM_REQUEST)
    public WSEndpointReference getFaultToFromRequest() {
      return _faultToFromRequest;
    }

    public void setFaultToFromRequest(WSEndpointReference ref) {
      _faultToFromRequest = ref;
    }

    private String _msgIdFromRequest = null;

    @Property(WSA_MSGID_FROM_REQUEST)
    public String getMessageIdFromRequest() {
      return _msgIdFromRequest;
    }

    public void setMessageIdFromRequest(String id) {
      _msgIdFromRequest = id;
    }
}
