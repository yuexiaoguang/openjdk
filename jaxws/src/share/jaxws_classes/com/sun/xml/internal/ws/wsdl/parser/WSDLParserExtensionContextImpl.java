package com.sun.xml.internal.ws.wsdl.parser;

import com.sun.xml.internal.ws.api.model.wsdl.editable.EditableWSDLModel;
import com.sun.xml.internal.ws.api.server.Container;
import com.sun.xml.internal.ws.api.wsdl.parser.WSDLParserExtensionContext;
import com.sun.xml.internal.ws.api.policy.PolicyResolver;

/**
 * Provides implementation of {@link WSDLParserExtensionContext}
 */
final class WSDLParserExtensionContextImpl implements WSDLParserExtensionContext {
    private final boolean isClientSide;
    private final EditableWSDLModel wsdlModel;
    private final Container container;
    private final PolicyResolver policyResolver;

    /**
     * Construct {@link WSDLParserExtensionContextImpl} with information that whether its on client side
     * or server side.
     */
    protected WSDLParserExtensionContextImpl(EditableWSDLModel model, boolean isClientSide, Container container, PolicyResolver policyResolver) {
        this.wsdlModel = model;
        this.isClientSide = isClientSide;
        this.container = container;
        this.policyResolver = policyResolver;
    }

    public boolean isClientSide() {
        return isClientSide;
    }

    public EditableWSDLModel getWSDLModel() {
        return wsdlModel;
    }

    public Container getContainer() {
        return this.container;
    }

    public PolicyResolver getPolicyResolver() {
        return policyResolver;
    }
}
