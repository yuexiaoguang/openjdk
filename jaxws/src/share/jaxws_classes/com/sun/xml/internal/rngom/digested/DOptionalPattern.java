package com.sun.xml.internal.rngom.digested;

public class DOptionalPattern extends DUnaryPattern {
    public boolean isNullable() {
        return true;
    }
    public Object accept( DPatternVisitor visitor ) {
        return visitor.onOptional(this);
    }
}
