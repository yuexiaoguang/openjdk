package com.sun.xml.internal.ws.api.pipe;

import com.sun.istack.internal.NotNull;
import com.sun.xml.internal.ws.api.message.Message;

import javax.xml.stream.XMLStreamReader;

public interface SOAPBindingCodec extends Codec {
    StreamSOAPCodec getXMLCodec();
}
