package com.sun.xml.internal.rngom.digested;

public class DNotAllowedPattern extends DPattern {
    public boolean isNullable() {
        return false;
    }
    public Object accept( DPatternVisitor visitor ) {
        return visitor.onNotAllowed(this);
    }
}
