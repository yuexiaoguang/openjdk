package com.sun.codemodel.internal;


/**
 * Common interface for code components that can generate
 * uses of themselves as statements.
 */
public interface JStatement {

    public void state(JFormatter f);

}
