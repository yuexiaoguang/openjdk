package com.sun.tools.internal.xjc.reader.xmlschema.bindinfo;

import javax.xml.bind.annotation.XmlEnumValue;

/**
 * Represents three constants of globalBindings/@optionalProperty.
 */
public enum OptionalPropertyMode {
    @XmlEnumValue("primitive")
    PRIMITIVE,
    @XmlEnumValue("wrapper")
    WRAPPER,
    @XmlEnumValue("isSet")
    ISSET
}
