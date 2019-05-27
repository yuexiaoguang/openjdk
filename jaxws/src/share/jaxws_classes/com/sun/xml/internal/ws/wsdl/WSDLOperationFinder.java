package com.sun.xml.internal.ws.wsdl;

import com.sun.xml.internal.ws.api.message.Packet;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLBoundOperation;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLPort;
import com.sun.xml.internal.ws.api.model.JavaMethod;
import com.sun.xml.internal.ws.api.model.SEIModel;
import com.sun.xml.internal.ws.api.model.WSDLOperationMapping;
import com.sun.xml.internal.ws.api.WSBinding;
import com.sun.xml.internal.ws.model.JavaMethodImpl;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import javax.xml.namespace.QName;

/**
 * Extensions if this class will be used for dispatching the request message to the correct endpoint method by
 * identifying the wsdl operation associated with the request.
 */
public abstract class WSDLOperationFinder {
    protected final WSDLPort wsdlModel;
    protected final WSBinding binding;
    protected final SEIModel seiModel;

    public WSDLOperationFinder(@NotNull WSDLPort wsdlModel, @NotNull WSBinding binding, @Nullable SEIModel seiModel) {
        this.wsdlModel = wsdlModel;
        this.binding = binding;
        this.seiModel= seiModel;
    }

    /**
     * This methods returns the QName of the WSDL operation correponding to a request Packet.
     * An implementation should return null when it cannot dispatch to a unique method based on the information it processes.
     * In such case, other OperationFinders are queried to resolve a WSDL operation.
     * It should throw an instance of DispatchException if it finds incorrect information in the packet.
     *
     * @deprecated use getWSDLOperationMapping(Packet request)
     *
     * @param request  Request Packet that is used to find the associated WSDLOperation
     * @return QName of the WSDL Operation that this request correponds to.
     *          null when it cannot find a unique operation to dispatch to.
     * @throws DispatchException When the information in the Packet is invalid
     */
    public QName getWSDLOperationQName(Packet request) throws DispatchException {
        WSDLOperationMapping m = getWSDLOperationMapping(request);
        return m != null ? m.getOperationName() : null;
    }

    public WSDLOperationMapping getWSDLOperationMapping(Packet request) throws DispatchException {
        return null;
    }

    protected WSDLOperationMapping wsdlOperationMapping(JavaMethodImpl j) {
        return new WSDLOperationMappingImpl(j.getOperation(), j);
    }

    protected WSDLOperationMapping wsdlOperationMapping(WSDLBoundOperation o) {
        return new WSDLOperationMappingImpl(o, null);
    }

    static class WSDLOperationMappingImpl implements WSDLOperationMapping {
        private WSDLBoundOperation wsdlOperation;
        private JavaMethod javaMethod;
        private QName operationName;

        WSDLOperationMappingImpl(WSDLBoundOperation wsdlOperation, JavaMethodImpl javaMethod) {
            this.wsdlOperation = wsdlOperation;
            this.javaMethod = javaMethod;
            operationName = (javaMethod != null) ? javaMethod.getOperationQName() : wsdlOperation.getName();
        }

        public WSDLBoundOperation getWSDLBoundOperation() {
            return wsdlOperation;
        }

        public JavaMethod getJavaMethod() {
            return javaMethod;
        }

        public QName getOperationName() {
            return operationName;
        }
    }
}
