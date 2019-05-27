package com.sun.tools.internal.xjc.reader.xmlschema.bindinfo;

import javax.xml.bind.annotation.XmlAttribute;


/**
 * This customization will enable serialization support on XJC.
 * This is used as a child of a {@link BIGlobalBinding} object,
 * and this doesn't implement BIDeclaration by itself.
 */
public final class BISerializable {

    /** serial version UID, or null to avoid generating the serialVersionUID field. */
    @XmlAttribute
    public Long uid;
}
