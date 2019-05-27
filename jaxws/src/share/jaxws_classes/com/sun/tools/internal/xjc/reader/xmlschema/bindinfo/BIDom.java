package com.sun.tools.internal.xjc.reader.xmlschema.bindinfo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.namespace.QName;

import com.sun.tools.internal.xjc.reader.Const;

/**
 * DOM customization.
 */
@XmlRootElement(name="dom")
public class BIDom extends AbstractDeclarationImpl {

    // unsupported yet
    @XmlAttribute
    String type;

    public final QName getName() { return NAME; }

    /** Name of the conversion declaration. */
    public static final QName NAME = new QName(Const.JAXB_NSURI,"dom");
}
