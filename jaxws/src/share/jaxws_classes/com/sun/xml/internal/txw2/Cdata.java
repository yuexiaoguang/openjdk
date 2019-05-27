package com.sun.xml.internal.txw2;

/**
 * CDATA section.
 */
final class Cdata extends Text {
    Cdata(Document document, NamespaceResolver nsResolver, Object obj) {
        super(document, nsResolver, obj);
    }

    void accept(ContentVisitor visitor) {
        visitor.onCdata(buffer);
    }
}
