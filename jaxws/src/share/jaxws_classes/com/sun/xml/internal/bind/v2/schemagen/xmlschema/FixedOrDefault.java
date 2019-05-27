package com.sun.xml.internal.bind.v2.schemagen.xmlschema;

import com.sun.xml.internal.txw2.TypedXmlWriter;
import com.sun.xml.internal.txw2.annotation.XmlAttribute;

/**
 * <p><b>
 *     Auto-generated, do not edit.
 * </b></p>
 */
public interface FixedOrDefault
    extends TypedXmlWriter
{


    @XmlAttribute("default")
    public FixedOrDefault _default(String value);

    @XmlAttribute
    public FixedOrDefault fixed(String value);

}
