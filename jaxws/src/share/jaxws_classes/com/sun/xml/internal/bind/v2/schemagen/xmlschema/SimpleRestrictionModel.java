package com.sun.xml.internal.bind.v2.schemagen.xmlschema;

import javax.xml.namespace.QName;
import com.sun.xml.internal.txw2.TypedXmlWriter;
import com.sun.xml.internal.txw2.annotation.XmlAttribute;
import com.sun.xml.internal.txw2.annotation.XmlElement;

/**
 * <p><b>
 *     Auto-generated, do not edit.
 * </b></p>
 */
public interface SimpleRestrictionModel
    extends SimpleTypeHost, TypedXmlWriter
{


    @XmlAttribute
    public SimpleRestrictionModel base(QName value);

    @XmlElement
    public NoFixedFacet enumeration();

}
