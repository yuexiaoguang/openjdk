package com.sun.xml.internal.xsom.visitor;

import com.sun.xml.internal.xsom.XSElementDecl;
import com.sun.xml.internal.xsom.XSModelGroup;
import com.sun.xml.internal.xsom.XSModelGroupDecl;
import com.sun.xml.internal.xsom.XSWildcard;

/**
 * Visitor that works on {@link com.sun.xml.internal.xsom.XSTerm}.
 */
public interface XSTermVisitor {
    void wildcard( XSWildcard wc );
    void modelGroupDecl( XSModelGroupDecl decl );
    void modelGroup( XSModelGroup group );
    void elementDecl( XSElementDecl decl );
}
