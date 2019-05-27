package com.sun.xml.internal.bind.v2.runtime.reflect.opt;

import com.sun.xml.internal.bind.DatatypeConverterImpl;
import com.sun.xml.internal.bind.v2.runtime.reflect.TransducedAccessor;
import com.sun.xml.internal.bind.v2.runtime.reflect.DefaultTransducedAccessor;

/**
 * Template {@link TransducedAccessor} for a byte field.
 *
 * <p>
 * All the TransducedAccessor_field are generated from <code>TransducedAccessor_field_B y t e</code>
 */
public final class TransducedAccessor_field_Byte extends DefaultTransducedAccessor {
    public String print(Object o) {
        return DatatypeConverterImpl._printByte( ((Bean)o).f_byte );
    }

    public void parse(Object o, CharSequence lexical) {
        ((Bean)o).f_byte=DatatypeConverterImpl._parseByte(lexical);
    }

    public boolean hasValue(Object o) {
        return true;
    }
}
