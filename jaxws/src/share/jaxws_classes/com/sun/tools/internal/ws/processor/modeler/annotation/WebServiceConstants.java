package com.sun.tools.internal.ws.processor.modeler.annotation;

public enum WebServiceConstants {

    SERVICE("Service"),

    JAXWS_PACKAGE_PD("jaxws."),

    PD_JAXWS_PACKAGE_PD(".jaxws."),

    BEAN("Bean"),

    FAULT_INFO("faultInfo"),

    RESPONSE("Response");

    private String value;

    private WebServiceConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
