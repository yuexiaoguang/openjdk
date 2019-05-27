package com.sun.xml.internal.ws.wsdl.writer.document;

import com.sun.xml.internal.txw2.TypedXmlWriter;
import com.sun.xml.internal.txw2.annotation.XmlElement;

public interface Documented
    extends TypedXmlWriter
{


    @XmlElement
    public com.sun.xml.internal.ws.wsdl.writer.document.Documented documentation(String value);

}
