package com.sun.xml.internal.ws.wsdl.writer.document;

import com.sun.xml.internal.txw2.TypedXmlWriter;
import com.sun.xml.internal.txw2.annotation.XmlAttribute;
import com.sun.xml.internal.txw2.annotation.XmlElement;
import com.sun.xml.internal.ws.wsdl.writer.document.Documented;

@XmlElement("import")
public interface Import
    extends TypedXmlWriter, Documented
{


    @XmlAttribute
    public com.sun.xml.internal.ws.wsdl.writer.document.Import location(String value);

    @XmlAttribute
    public com.sun.xml.internal.ws.wsdl.writer.document.Import namespace(String value);

}
