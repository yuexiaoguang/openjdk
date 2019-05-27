package com.sun.xml.internal.rngom.digested;

public class DOneOrMorePattern extends DUnaryPattern {
    public boolean isNullable() {
        return getChild().isNullable();
    }
    public Object accept( DPatternVisitor visitor ) {
        return visitor.onOneOrMore(this);
    }
}
