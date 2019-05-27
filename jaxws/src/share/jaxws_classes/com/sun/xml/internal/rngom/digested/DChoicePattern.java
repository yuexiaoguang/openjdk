package com.sun.xml.internal.rngom.digested;

/**
 * &lt;choice> pattern.
 */
public class DChoicePattern extends DContainerPattern {
    public boolean isNullable() {
        for( DPattern p=firstChild(); p!=null; p=p.next )
            if(p.isNullable())
                return true;
        return false;
    }
    public <V> V accept( DPatternVisitor<V> visitor ) {
        return visitor.onChoice(this);
    }
}
