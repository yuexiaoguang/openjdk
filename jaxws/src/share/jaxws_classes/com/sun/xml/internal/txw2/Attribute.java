package com.sun.xml.internal.txw2;

final class Attribute {
    final String nsUri;
    final String localName;

    /**
     * Attributes of an element form a linked list.
     */
    Attribute next;

    /**
     * Attribute value that potentially contains dummy prefixes.
     */
    final StringBuilder value = new StringBuilder();

    Attribute(String nsUri, String localName) {
        assert nsUri!=null && localName!=null;

        this.nsUri = nsUri;
        this.localName = localName;
    }

    boolean hasName( String nsUri, String localName ) {
        return this.localName.equals(localName) && this.nsUri.equals(nsUri);
    }
}