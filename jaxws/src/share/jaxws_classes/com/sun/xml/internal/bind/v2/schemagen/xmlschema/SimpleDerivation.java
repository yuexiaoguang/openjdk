package com.sun.xml.internal.bind.v2.schemagen.xmlschema;

import com.sun.xml.internal.txw2.TypedXmlWriter;
import com.sun.xml.internal.txw2.annotation.XmlElement;

/**
 * <p><b>
 *     Auto-generated, do not edit.
 * </b></p>
 */
public interface SimpleDerivation
    extends TypedXmlWriter
{


    @XmlElement
    public SimpleRestriction restriction();

    @XmlElement
    public Union union();

    @XmlElement
    public List list();

}
