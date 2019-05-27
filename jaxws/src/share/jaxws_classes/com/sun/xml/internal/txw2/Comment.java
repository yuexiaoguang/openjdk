package com.sun.xml.internal.txw2;

/**
 * Comment.
 */
final class Comment extends Content {
    /**
     * The text to be writtten.
     */
    private final StringBuilder buffer = new StringBuilder();

    public Comment(Document document, NamespaceResolver nsResolver, Object obj) {
        document.writeValue(obj,nsResolver,buffer);
    }

    boolean concludesPendingStartTag() {
        return false;
    }

    void accept(ContentVisitor visitor) {
        visitor.onComment(buffer);
    }
}
