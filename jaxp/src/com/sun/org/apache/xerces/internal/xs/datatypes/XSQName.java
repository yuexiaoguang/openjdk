package com.sun.org.apache.xerces.internal.xs.datatypes;

/**
 * Interface to expose QName actual values
 */
public interface XSQName {

    /**
     * @return com.sun.org.apache.xerces.internal.xni.QName class instance
     */
    public com.sun.org.apache.xerces.internal.xni.QName getXNIQName();

    /**
     * @return javax.xml.namespace.QName class instance
     */
    public javax.xml.namespace.QName getJAXPQName();
}
