package com.sun.xml.internal.ws.wsdl.writer.document;

import com.sun.xml.internal.txw2.TypedXmlWriter;
import com.sun.xml.internal.txw2.annotation.XmlAttribute;
import com.sun.xml.internal.txw2.annotation.XmlElement;
import com.sun.xml.internal.ws.wsdl.writer.document.Documented;
import com.sun.xml.internal.ws.wsdl.writer.document.Port;

@XmlElement("service")
public interface Service
    extends TypedXmlWriter, Documented
{


    @XmlAttribute
    public com.sun.xml.internal.ws.wsdl.writer.document.Service name(String value);

    @XmlElement
    public Port port();

}
