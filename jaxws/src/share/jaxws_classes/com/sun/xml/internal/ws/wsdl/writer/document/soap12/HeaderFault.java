package com.sun.xml.internal.ws.wsdl.writer.document.soap12;

import javax.xml.namespace.QName;
import com.sun.xml.internal.txw2.TypedXmlWriter;
import com.sun.xml.internal.txw2.annotation.XmlAttribute;
import com.sun.xml.internal.txw2.annotation.XmlElement;

@XmlElement("headerFault")
public interface HeaderFault
    extends TypedXmlWriter, BodyType
{


    @XmlAttribute
    public com.sun.xml.internal.ws.wsdl.writer.document.soap12.HeaderFault message(QName value);

}
