package com.sun.xml.internal.txw2;

final class EndTag extends Content {
    boolean concludesPendingStartTag() {
        return true;
    }

    void accept(ContentVisitor visitor) {
        visitor.onEndTag();
    }
}
