package com.sun.xml.internal.bind.v2.schemagen.xmlschema;

import com.sun.xml.internal.txw2.TypedXmlWriter;
import com.sun.xml.internal.txw2.annotation.XmlAttribute;

/**
 * <p><b>
 *     Auto-generated, do not edit.
 * </b></p>
 */
public interface Wildcard
    extends Annotated, TypedXmlWriter
{


    @XmlAttribute
    public Wildcard processContents(String value);

    @XmlAttribute
    public Wildcard namespace(String[] value);

    @XmlAttribute
    public Wildcard namespace(String value);

}
