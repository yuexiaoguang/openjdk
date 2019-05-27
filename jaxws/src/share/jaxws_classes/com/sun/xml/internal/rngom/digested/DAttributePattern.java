package com.sun.xml.internal.rngom.digested;

import com.sun.xml.internal.rngom.nc.NameClass;

public class DAttributePattern extends DXmlTokenPattern {
    public DAttributePattern(NameClass name) {
        super(name);
    }
    public Object accept( DPatternVisitor visitor ) {
        return visitor.onAttribute(this);
    }
}
