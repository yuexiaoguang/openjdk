package com.sun.tools.internal.xjc.reader.xmlschema.bindinfo;

import javax.xml.bind.annotation.XmlEnumValue;

public enum LocalScoping {
    @XmlEnumValue("nested")
    NESTED,
    @XmlEnumValue("toplevel")
    TOPLEVEL
}
