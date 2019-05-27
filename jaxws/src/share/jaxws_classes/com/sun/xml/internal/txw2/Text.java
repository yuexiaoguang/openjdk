package com.sun.xml.internal.txw2;

/**
 * {@link Pcdata} or {@link Cdata}.
 */
abstract class Text extends Content {
    /**
     * The text to be writtten.
     */
    protected final StringBuilder buffer = new StringBuilder();

    protected Text(Document document, NamespaceResolver nsResolver, Object obj) {
        document.writeValue(obj,nsResolver,buffer);
    }

    boolean concludesPendingStartTag() {
        return false;
    }
}
