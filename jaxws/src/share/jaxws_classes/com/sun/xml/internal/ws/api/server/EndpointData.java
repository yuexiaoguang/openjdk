package com.sun.xml.internal.ws.api.server;

import javax.xml.namespace.QName;

public abstract class EndpointData {

    public abstract String getNamespace();

    public abstract String getServiceName();

    public abstract String getPortName();

    public abstract String getImplClass();

}
