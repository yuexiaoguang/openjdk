package com.sun.xml.internal.bind.v2.schemagen.xmlschema;

import com.sun.xml.internal.txw2.TypedXmlWriter;
import com.sun.xml.internal.txw2.annotation.XmlAttribute;
import com.sun.xml.internal.txw2.annotation.XmlElement;

/**
 * <p><b>
 *     Auto-generated, do not edit.
 * </b></p>
 */
@XmlElement("annotation")
public interface Annotation
    extends TypedXmlWriter
{


    @XmlElement
    public Appinfo appinfo();

    @XmlElement
    public Documentation documentation();

    @XmlAttribute
    public Annotation id(String value);

}
