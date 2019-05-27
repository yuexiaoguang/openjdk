package com.sun.xml.internal.bind.v2.runtime.unmarshaller;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import com.sun.xml.internal.org.jvnet.staxex.XMLStreamReaderEx;
import org.xml.sax.SAXException;

/**
 * Reads XML from StAX {@link XMLStreamReader} and
 * feeds events to {@link XmlVisitor}.
 */
final class StAXExConnector extends StAXStreamConnector {

    // StAX event source
    private final XMLStreamReaderEx in;

    public StAXExConnector(XMLStreamReaderEx in, XmlVisitor visitor) {
        super(in,visitor);
        this.in = in;
    }

    @Override
    protected void handleCharacters() throws XMLStreamException, SAXException {
        if( predictor.expectText() ) {
            CharSequence pcdata = in.getPCDATA();
            if(pcdata instanceof com.sun.xml.internal.org.jvnet.staxex.Base64Data) {
                com.sun.xml.internal.org.jvnet.staxex.Base64Data bd = (com.sun.xml.internal.org.jvnet.staxex.Base64Data) pcdata;
                Base64Data binary = new Base64Data();
                if(!bd.hasData())
                    binary.set(bd.getDataHandler());
                else
                    binary.set( bd.get(), bd.getDataLen(), bd.getMimeType() );
                // we make an assumption here that the binary data shows up on its own
                // not adjacent to other text. So it's OK to fire it off right now.
                visitor.text(binary);
                textReported = true;
            } else {
                buffer.append(pcdata);
            }
        }
    }
}
