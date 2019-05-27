package com.sun.xml.internal.xsom.visitor;

import com.sun.xml.internal.xsom.XSListSimpleType;
import com.sun.xml.internal.xsom.XSRestrictionSimpleType;
import com.sun.xml.internal.xsom.XSUnionSimpleType;

/**
 * Visitor that works on {@link com.sun.xml.internal.xsom.XSSimpleType}
 * and its derived interfaces.
 */
public interface XSSimpleTypeVisitor {
    void listSimpleType( XSListSimpleType type );
    void unionSimpleType( XSUnionSimpleType type );
    void restrictionSimpleType( XSRestrictionSimpleType type );
}
