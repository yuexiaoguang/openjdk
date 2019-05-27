package com.sun.xml.internal.ws.api.model.wsdl.editable;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLMessage;

public interface EditableWSDLMessage extends WSDLMessage {

    @Override
    Iterable<? extends EditableWSDLPart> parts();

    /**
     * Add part
     *
     * @param part Part
     */
    public void add(EditableWSDLPart part);

}
