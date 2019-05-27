package com.sun.xml.internal.dtdparser;

/**
 * Base class for entity declarations as used by the parser.
 */
class EntityDecl {
    String name;        // <!ENTITY name ... >

    boolean isFromInternalSubset;
    boolean isPE;
}
