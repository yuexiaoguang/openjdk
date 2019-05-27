package com.sun.xml.internal.xsom;


/**
 * Named model group declaration.
 */
public interface XSModelGroupDecl extends XSDeclaration, XSTerm
{
    /**
     * Gets the body of this declaration.
     */
    XSModelGroup getModelGroup();
}
