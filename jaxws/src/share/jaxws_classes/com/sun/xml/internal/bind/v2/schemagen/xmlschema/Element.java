package com.sun.xml.internal.bind.v2.schemagen.xmlschema;

import javax.xml.namespace.QName;
import com.sun.xml.internal.txw2.TypedXmlWriter;
import com.sun.xml.internal.txw2.annotation.XmlAttribute;

/**
 * <p><b>
 *     Auto-generated, do not edit.
 * </b></p>
 */
public interface Element
    extends Annotated, ComplexTypeHost, FixedOrDefault, SimpleTypeHost, TypedXmlWriter
{


    @XmlAttribute
    public Element type(QName value);

    @XmlAttribute
    public Element block(String[] value);

    @XmlAttribute
    public Element block(String value);

    @XmlAttribute
    public Element nillable(boolean value);

}
