package com.sun.xml.internal.bind.v2.model.util;

import javax.xml.namespace.QName;

import com.sun.xml.internal.bind.v2.TODO;
import com.sun.xml.internal.bind.v2.WellKnownNamespace;

/**
 * Util class for ArrayInfo
 */
public class ArrayInfoUtil {

    private ArrayInfoUtil() {}

    /**
     * Computes the type name of the array from that of the item type.
     */
    public static QName calcArrayTypeName(QName n) {
        String uri;
        if(n.getNamespaceURI().equals(WellKnownNamespace.XML_SCHEMA)) {
            TODO.checkSpec("this URI");
            uri = "http://jaxb.dev.java.net/array";
        } else
            uri = n.getNamespaceURI();
        return new QName(uri,n.getLocalPart()+"Array");
    }
}
