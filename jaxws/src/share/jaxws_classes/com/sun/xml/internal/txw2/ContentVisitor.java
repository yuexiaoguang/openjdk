package com.sun.xml.internal.txw2;

/**
 * Visits {@link Content}.
 */
interface ContentVisitor {
    void onStartDocument();

    void onEndDocument();

    void onEndTag();

    void onPcdata(StringBuilder buffer);

    void onCdata(StringBuilder buffer);

    void onStartTag(String nsUri, String localName, Attribute attributes, NamespaceDecl namespaces);

    void onComment(StringBuilder buffer);
}
