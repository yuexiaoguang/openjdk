package com.sun.xml.internal.xsom;

/**
 * Attribute declaration.
 */
public interface XSAttributeDecl extends XSDeclaration
{
    XSSimpleType getType();

    XmlString getDefaultValue();
    XmlString getFixedValue();
}
