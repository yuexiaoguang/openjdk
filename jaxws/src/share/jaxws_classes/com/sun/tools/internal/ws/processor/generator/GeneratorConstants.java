package com.sun.tools.internal.ws.processor.generator;

public enum GeneratorConstants {

    DOTC("."),

    SIG_INNERCLASS("$"),

    JAVA_SRC_SUFFIX(".java"),

    QNAME_SUFFIX("_QNAME"),

    GET("get"),

    IS("is"),

    RESPONSE("Response"),

    FAULT_CLASS_MEMBER_NAME("faultInfo");

    private String value;

    private GeneratorConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
