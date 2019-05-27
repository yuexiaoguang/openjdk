package com.sun.xml.internal.messaging.saaj.packaging.mime;


/**
 * The Header class stores a name/value pair to represent headers.
 */
public interface Header {

    /**
     * Returns the name of this header.
     *
     * @return          name of the header
     */
    String getName();

    /**
     * Returns the value of this header.
     *
     * @return          value of the header
     */
    String getValue();
}
