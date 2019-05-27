package com.sun.xml.internal.ws.api.databinding;

import com.oracle.webservices.internal.api.databinding.JavaCallInfo;
import com.sun.xml.internal.ws.api.message.Packet;
import com.sun.xml.internal.ws.api.model.JavaMethod;

public interface EndpointCallBridge {

        public JavaCallInfo deserializeRequest(Packet req);

        //Change the return type to??
        public Packet serializeResponse(JavaCallInfo call);

    JavaMethod getOperationModel();
}
