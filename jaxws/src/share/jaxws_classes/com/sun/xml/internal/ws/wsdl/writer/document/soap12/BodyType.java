package com.sun.xml.internal.ws.wsdl.writer.document.soap12;

import com.sun.xml.internal.txw2.TypedXmlWriter;
import com.sun.xml.internal.txw2.annotation.XmlAttribute;

public interface BodyType
    extends TypedXmlWriter
{


    @XmlAttribute
    public com.sun.xml.internal.ws.wsdl.writer.document.soap12.BodyType encodingStyle(String value);

    @XmlAttribute
    public com.sun.xml.internal.ws.wsdl.writer.document.soap12.BodyType namespace(String value);

    @XmlAttribute
    public com.sun.xml.internal.ws.wsdl.writer.document.soap12.BodyType use(String value);

    @XmlAttribute
    public com.sun.xml.internal.ws.wsdl.writer.document.soap12.BodyType parts(String value);

}
