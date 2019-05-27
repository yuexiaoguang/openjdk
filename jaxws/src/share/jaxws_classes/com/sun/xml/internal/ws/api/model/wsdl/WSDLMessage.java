package com.sun.xml.internal.ws.api.model.wsdl;

import javax.xml.namespace.QName;

/**
 * Abstraction of wsdl:message.
 */
public interface WSDLMessage extends WSDLObject, WSDLExtensible {
    /**
     * Gives wsdl:message@name value.
     */
    QName getName();

    /**
     * Gets all the parts.
     */
    Iterable<? extends WSDLPart> parts();
}
