package com.sun.xml.internal.ws.wsdl.writer;

import com.sun.xml.internal.txw2.TypedXmlWriter;
import com.sun.xml.internal.txw2.annotation.XmlAttribute;
import com.sun.xml.internal.txw2.annotation.XmlElement;
import com.sun.xml.internal.ws.addressing.W3CAddressingConstants;
import com.sun.xml.internal.ws.wsdl.writer.document.StartWithExtensionsType;

@XmlElement(value = W3CAddressingConstants.WSA_NAMESPACE_WSDL_NAME,
            ns = W3CAddressingConstants.WSAW_USING_ADDRESSING_NAME)
public interface UsingAddressing extends TypedXmlWriter, StartWithExtensionsType {
    @XmlAttribute(value = "required", ns = "http://schemas.xmlsoap.org/wsdl/")
    public void required(boolean b);
}
