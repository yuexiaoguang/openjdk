package com.sun.xml.internal.bind.v2.schemagen.xmlschema;

import com.sun.xml.internal.txw2.TypedXmlWriter;
import com.sun.xml.internal.txw2.annotation.XmlElement;

/**
 * <p><b>
 *     Auto-generated, do not edit.
 * </b></p>
 */
public interface TypeDefParticle
    extends TypedXmlWriter
{


    @XmlElement
    public ExplicitGroup all();

    @XmlElement
    public ExplicitGroup sequence();

    @XmlElement
    public ExplicitGroup choice();

}
