package com.sun.xml.internal.bind.v2.runtime.reflect.opt;

import com.sun.xml.internal.bind.DatatypeConverterImpl;
import com.sun.xml.internal.bind.v2.runtime.reflect.TransducedAccessor;
import com.sun.xml.internal.bind.v2.runtime.reflect.DefaultTransducedAccessor;

/**
 * Template {@link TransducedAccessor} for a boolean field.
 * <p><b>
 *     Auto-generated, do not edit.
 * </b></p>
 * <p>
 *     All the TransducedAccessor_field are generated from <code>TransducedAccessor_field_B y t e</code>
 * </p>
 */
public final class TransducedAccessor_method_Boolean extends DefaultTransducedAccessor {
    public String print(Object o) {
        return DatatypeConverterImpl._printBoolean( ((Bean)o).get_boolean() );
    }

    public void parse(Object o, CharSequence lexical) {
        ((Bean)o).set_boolean(DatatypeConverterImpl._parseBoolean(lexical));
    }

    public boolean hasValue(Object o) {
        return true;
    }
//
//    public void writeLeafElement(Object o, QName tagName, String fieldName, XMLSerializer w) throws SAXException, AccessorException {
//        w.leafElement(tagName, ((Bean)o).get_boolean(), fieldName );
//    }
}
