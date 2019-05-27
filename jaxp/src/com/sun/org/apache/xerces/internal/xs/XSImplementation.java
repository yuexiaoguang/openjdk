/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xerces.internal.xs;

/**
 * This interface allows one to retrieve an instance of <code>XSLoader</code>.
 * This interface should be implemented on the same object that implements
 * DOMImplementation.
 */
public interface XSImplementation {
    /**
     * A list containing the versions of XML Schema documents recognized by
     * this <code>XSImplemenation</code>.
     */
    public StringList getRecognizedVersions();


    /**
     * Creates a new XSLoader. The newly constructed loader may then be
     * configured and used to load XML Schemas.
     * @param versions  A list containing the versions of XML Schema
     *   documents which can be loaded by the <code>XSLoader</code> or
     *   <code>null</code> to permit XML Schema documents of any recognized
     *   version to be loaded by the XSLoader.
     * @return  An XML Schema loader.
     * @exception XSException
     *   NOT_SUPPORTED_ERR: Raised if the implementation does not support one
     *   of the specified versions.
     */
    public XSLoader createXSLoader(StringList versions)
                                   throws XSException;

}
