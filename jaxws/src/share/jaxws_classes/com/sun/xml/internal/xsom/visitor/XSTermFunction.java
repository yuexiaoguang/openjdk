package com.sun.xml.internal.xsom.visitor;

import com.sun.xml.internal.xsom.XSElementDecl;
import com.sun.xml.internal.xsom.XSModelGroup;
import com.sun.xml.internal.xsom.XSModelGroupDecl;
import com.sun.xml.internal.xsom.XSWildcard;
import com.sun.xml.internal.xsom.XSTerm;

/**
 * Function object that works on {@link XSTerm}.
 */
public interface XSTermFunction<T> {
    T wildcard( XSWildcard wc );
    T modelGroupDecl( XSModelGroupDecl decl );
    T modelGroup( XSModelGroup group );
    T elementDecl( XSElementDecl decl );
}
