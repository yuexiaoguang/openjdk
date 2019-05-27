package com.sun.xml.internal.bind.v2.schemagen.xmlschema;

import javax.xml.namespace.QName;
import com.sun.xml.internal.txw2.TypedXmlWriter;
import com.sun.xml.internal.txw2.annotation.XmlAttribute;

/**
 * <p><b>
 *     Auto-generated, do not edit.
 * </b></p>
 */
public interface AttributeType
    extends SimpleTypeHost, TypedXmlWriter
{


    @XmlAttribute
    public AttributeType type(QName value);

}
