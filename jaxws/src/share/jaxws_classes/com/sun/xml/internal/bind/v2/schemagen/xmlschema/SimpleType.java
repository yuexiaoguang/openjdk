package com.sun.xml.internal.bind.v2.schemagen.xmlschema;

import com.sun.xml.internal.txw2.TypedXmlWriter;
import com.sun.xml.internal.txw2.annotation.XmlAttribute;
import com.sun.xml.internal.txw2.annotation.XmlElement;

/**
 * <p><b>
 *     Auto-generated, do not edit.
 * </b></p>
 */
@XmlElement("simpleType")
public interface SimpleType
    extends Annotated, SimpleDerivation, TypedXmlWriter
{


    @XmlAttribute("final")
    public SimpleType _final(String value);

    @XmlAttribute("final")
    public SimpleType _final(String[] value);

    @XmlAttribute
    public SimpleType name(String value);

}
