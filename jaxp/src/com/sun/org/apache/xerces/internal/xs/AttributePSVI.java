package com.sun.org.apache.xerces.internal.xs;

/**
 *  Represents a PSVI item for one attribute information item.
 */
public interface AttributePSVI extends ItemPSVI {
    /**
     * [attribute declaration]: An item isomorphic to the declaration
     * component itself.
     */
    public XSAttributeDeclaration getAttributeDeclaration();

}
