package com.sun.xml.internal.bind.v2.schemagen.xmlschema;

import com.sun.xml.internal.txw2.TypedXmlWriter;
import com.sun.xml.internal.txw2.annotation.XmlAttribute;
import com.sun.xml.internal.txw2.annotation.XmlElement;

/**
 * <p><b>
 *     Auto-generated, do not edit.
 * </b></p>
 */
@XmlElement("attribute")
public interface TopLevelAttribute
    extends Annotated, AttributeType, FixedOrDefault, TypedXmlWriter
{


    @XmlAttribute
    public TopLevelAttribute name(String value);

}
