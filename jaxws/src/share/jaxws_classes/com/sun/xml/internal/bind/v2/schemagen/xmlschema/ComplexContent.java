package com.sun.xml.internal.bind.v2.schemagen.xmlschema;

import com.sun.xml.internal.txw2.TypedXmlWriter;
import com.sun.xml.internal.txw2.annotation.XmlAttribute;
import com.sun.xml.internal.txw2.annotation.XmlElement;

/**
 * <p><b>
 *     Auto-generated, do not edit.
 * </b></p>
 */
@XmlElement("complexContent")
public interface ComplexContent
    extends Annotated, TypedXmlWriter
{


    @XmlElement
    public ComplexExtension extension();

    @XmlElement
    public ComplexRestriction restriction();

    @XmlAttribute
    public ComplexContent mixed(boolean value);

}
