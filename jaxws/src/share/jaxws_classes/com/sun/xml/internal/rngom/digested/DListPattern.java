package com.sun.xml.internal.rngom.digested;

public class DListPattern extends DUnaryPattern {
    public boolean isNullable() {
        return getChild().isNullable();
    }
    public Object accept( DPatternVisitor visitor ) {
        return visitor.onList(this);
    }
}
