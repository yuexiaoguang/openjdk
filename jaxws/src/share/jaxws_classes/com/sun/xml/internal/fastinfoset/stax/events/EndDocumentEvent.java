package com.sun.xml.internal.fastinfoset.stax.events ;

import javax.xml.stream.events.EndDocument;


public class EndDocumentEvent extends EventBase implements EndDocument {

    public EndDocumentEvent() {
        super(END_DOCUMENT);
    }

    public String toString() {
        return "<? EndDocument ?>";
    }

}
