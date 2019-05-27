package com.sun.xml.internal.bind.v2.runtime.reflect.opt;

import java.io.IOException;

import javax.xml.stream.XMLStreamException;

import com.sun.xml.internal.bind.DatatypeConverterImpl;
import com.sun.xml.internal.bind.api.AccessorException;
import com.sun.xml.internal.bind.v2.runtime.Name;
import com.sun.xml.internal.bind.v2.runtime.XMLSerializer;
import com.sun.xml.internal.bind.v2.runtime.reflect.TransducedAccessor;
import com.sun.xml.internal.bind.v2.runtime.reflect.DefaultTransducedAccessor;

import org.xml.sax.SAXException;

/**
 * Template {@link TransducedAccessor} for a byte field.
 *
 * <p>
 * All the TransducedAccessor_field are generated from <code>TransducedAccessor_field_B y t e</code>
 */
public final class TransducedAccessor_method_Integer extends DefaultTransducedAccessor {
    public String print(Object o) {
        return DatatypeConverterImpl._printInt( ((Bean)o).get_int() );
    }

    public void parse(Object o, CharSequence lexical) {
        ((Bean)o).set_int(DatatypeConverterImpl._parseInt(lexical));
    }

    public boolean hasValue(Object o) {
        return true;
    }

    @Override
    public void writeLeafElement(XMLSerializer w, Name tagName, Object o, String fieldName) throws SAXException, AccessorException, IOException, XMLStreamException {
        w.leafElement(tagName, ((Bean)o).get_int(), fieldName );
    }
}
