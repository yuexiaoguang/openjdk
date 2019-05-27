package com.sun.xml.internal.ws.api.databinding;

import java.lang.reflect.Method;
import com.oracle.webservices.internal.api.databinding.JavaCallInfo;
import com.sun.xml.internal.ws.api.message.Packet;
import com.sun.xml.internal.ws.api.model.JavaMethod;

public interface ClientCallBridge {

        Packet createRequestPacket(JavaCallInfo call);

        JavaCallInfo readResponse(Packet packet, JavaCallInfo call) throws Throwable;

        Method getMethod();

        JavaMethod getOperationModel();
}
