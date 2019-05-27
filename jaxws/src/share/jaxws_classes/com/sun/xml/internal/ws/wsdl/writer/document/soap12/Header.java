package com.sun.xml.internal.ws.wsdl.writer.document.soap12;

import javax.xml.namespace.QName;
import com.sun.xml.internal.txw2.TypedXmlWriter;
import com.sun.xml.internal.txw2.annotation.XmlAttribute;
import com.sun.xml.internal.txw2.annotation.XmlElement;

@XmlElement("header")
public interface Header
    extends TypedXmlWriter, BodyType
{


    @XmlAttribute
    public com.sun.xml.internal.ws.wsdl.writer.document.soap12.Header message(QName value);

    @XmlElement
    public HeaderFault headerFault();

    @XmlAttribute
    public BodyType part(String value);

}
