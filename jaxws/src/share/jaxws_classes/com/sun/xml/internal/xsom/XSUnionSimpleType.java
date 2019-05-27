package com.sun.xml.internal.xsom;

/**
 * Union simple type.
 */
public interface XSUnionSimpleType extends XSSimpleType, Iterable<XSSimpleType>
{
    XSSimpleType getMember(int idx);
    int getMemberSize();
}
