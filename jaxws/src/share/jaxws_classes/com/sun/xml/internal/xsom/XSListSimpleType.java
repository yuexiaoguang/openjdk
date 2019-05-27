package com.sun.xml.internal.xsom;

/**
 * List simple type.
 */
public interface XSListSimpleType extends XSSimpleType
{
    XSSimpleType getItemType();
}
