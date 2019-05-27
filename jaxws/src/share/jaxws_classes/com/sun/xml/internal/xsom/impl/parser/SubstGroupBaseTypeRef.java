package com.sun.xml.internal.xsom.impl.parser;

import com.sun.xml.internal.xsom.XSType;
import com.sun.xml.internal.xsom.impl.Ref;

public class SubstGroupBaseTypeRef implements Ref.Type {
    private final Ref.Element e;

    public SubstGroupBaseTypeRef( Ref.Element _e ) {
        this.e = _e;
    }

    public XSType getType() {
        return e.get().getType();
    }
}
