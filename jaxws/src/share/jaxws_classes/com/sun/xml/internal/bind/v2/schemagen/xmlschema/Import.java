package com.sun.xml.internal.bind.v2.schemagen.xmlschema;

import com.sun.xml.internal.txw2.TypedXmlWriter;
import com.sun.xml.internal.txw2.annotation.XmlAttribute;
import com.sun.xml.internal.txw2.annotation.XmlElement;

/**
 * <p><b>
 *     Auto-generated, do not edit.
 * </b></p>
 */
@XmlElement("import")
public interface Import
    extends Annotated, TypedXmlWriter
{


    @XmlAttribute
    public Import namespace(String value);

    @XmlAttribute
    public Import schemaLocation(String value);

}
