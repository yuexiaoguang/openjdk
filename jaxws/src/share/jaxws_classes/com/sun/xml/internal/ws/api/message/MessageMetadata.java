package com.sun.xml.internal.ws.api.message;

import com.sun.xml.internal.ws.api.model.WSDLOperationMapping;

/**
 * In order for the Message to get properties from the Packet ...
 */
public interface MessageMetadata {
    public WSDLOperationMapping getWSDLOperationMapping();
}
