package com.sun.xml.internal.rngom.digested;

public class DEmptyPattern extends DPattern {
    public boolean isNullable() {
        return true;
    }
    public Object accept( DPatternVisitor visitor ) {
        return visitor.onEmpty(this);
    }
}
