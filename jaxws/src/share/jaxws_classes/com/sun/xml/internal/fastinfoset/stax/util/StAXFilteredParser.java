package com.sun.xml.internal.fastinfoset.stax.util;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.StreamFilter;
import com.sun.xml.internal.fastinfoset.CommonResourceBundle;


public class StAXFilteredParser extends StAXParserWrapper {
    private StreamFilter _filter;

    /** Creates a new instance of StAXFilteredParser */
    public StAXFilteredParser() {
    }
    public StAXFilteredParser(XMLStreamReader reader, StreamFilter filter) {
        super(reader);
        _filter = filter;
    }

    public void setFilter(StreamFilter filter) {
        _filter = filter;
    }

    public int next() throws XMLStreamException
    {
        if (hasNext())
            return super.next();
        throw new IllegalStateException(CommonResourceBundle.getInstance().getString("message.noMoreItems"));
    }

    public boolean hasNext() throws XMLStreamException
    {
        while (super.hasNext()) {
            if (_filter.accept(getReader())) return true;
            super.next();
        }
        return false;
    }

}
