package com.sun.xml.internal.ws.wsdl.writer.document.soap;

import com.sun.xml.internal.txw2.TypedXmlWriter;
import com.sun.xml.internal.txw2.annotation.XmlAttribute;

public interface BodyType
    extends TypedXmlWriter
{


    @XmlAttribute
    public com.sun.xml.internal.ws.wsdl.writer.document.soap.BodyType encodingStyle(String value);

    @XmlAttribute
    public com.sun.xml.internal.ws.wsdl.writer.document.soap.BodyType namespace(String value);

    @XmlAttribute
    public com.sun.xml.internal.ws.wsdl.writer.document.soap.BodyType use(String value);

    @XmlAttribute
    public com.sun.xml.internal.ws.wsdl.writer.document.soap.BodyType parts(String value);

}
