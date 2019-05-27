package com.sun.xml.internal.rngom.digested;

public class DTextPattern extends DPattern {
    public boolean isNullable() {
        return true;
    }
    public Object accept( DPatternVisitor visitor ) {
        return visitor.onText(this);
    }
}
