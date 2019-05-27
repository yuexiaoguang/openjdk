package com.sun.xml.internal.ws.api.model.wsdl.editable;

import com.sun.istack.internal.NotNull;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

public interface EditableWSDLOutput extends WSDLOutput {

    @Override
    EditableWSDLMessage getMessage();

    @Override
    @NotNull
    EditableWSDLOperation getOperation();

    /**
     * Sets action
     *
     * @param action Action
     */
    public void setAction(String action);

    /**
     * Set to true if this is the default action
     *
     * @param defaultAction True, if default action
     */
    public void setDefaultAction(boolean defaultAction);

    /**
     * Freezes WSDL model to prevent further modification
     *
     * @param root WSDL Model
     */
    public void freeze(EditableWSDLModel root);

}
