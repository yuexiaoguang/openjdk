package com.sun.xml.internal.xsom;

/**
 * Notation declaration.
 */
public interface XSNotation extends XSDeclaration {
    String getPublicId();
    String getSystemId();
}
