package com.sun.xml.internal.ws.api.model;

import javax.xml.namespace.QName;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLBoundOperation;

/**
 * WSDLOperationMapping represents the mapping between a WSDL operation and a
 * JavaMethod. This is intended to be the output of resolving a Packet to the
 * targeting WSDL operation.
 */
public interface WSDLOperationMapping {

    WSDLBoundOperation getWSDLBoundOperation();

    JavaMethod getJavaMethod();

    /**
     * WSDL1.1 allows operation overloading on the operation name; the operation name should
     * NOT be used as identifier of the operation.
     */
    QName getOperationName();
}
