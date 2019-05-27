package com.sun.xml.internal.bind.v2.runtime.unmarshaller;

import com.sun.xml.internal.bind.util.AttributesImpl;

/**
 * {@link AttributesEx} implementation.
 *
 * TODO: proper implementation that holds CharSequence
 */
public final class AttributesExImpl extends AttributesImpl implements AttributesEx {
    public CharSequence getData(int idx) {
        return getValue(idx);
    }

    public CharSequence getData(String nsUri, String localName) {
        return getValue(nsUri,localName);
    }
}
