package com.sun.xml.internal.bind.v2.schemagen.xmlschema;

import com.sun.xml.internal.txw2.TypedXmlWriter;
import com.sun.xml.internal.txw2.annotation.XmlAttribute;

/**
 * <p><b>
 *     Auto-generated, do not edit.
 * </b></p>
 */
public interface Occurs
    extends TypedXmlWriter
{


    @XmlAttribute
    public Occurs minOccurs(int value);

    @XmlAttribute
    public Occurs maxOccurs(String value);

    @XmlAttribute
    public Occurs maxOccurs(int value);

}
