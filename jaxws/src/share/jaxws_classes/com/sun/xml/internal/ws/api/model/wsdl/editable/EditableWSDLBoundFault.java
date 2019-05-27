package com.sun.xml.internal.ws.api.model.wsdl.editable;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLBoundFault;

public interface EditableWSDLBoundFault extends WSDLBoundFault {

    @Override
    @Nullable
    EditableWSDLFault getFault();

    @Override
    @NotNull
    EditableWSDLBoundOperation getBoundOperation();

    /**
     * Freezes WSDL model to prevent further modification
     *
     * @param operation Operation
     */
    void freeze(EditableWSDLBoundOperation operation);
}
