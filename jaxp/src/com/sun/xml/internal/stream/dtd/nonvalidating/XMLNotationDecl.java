package com.sun.xml.internal.stream.dtd.nonvalidating;

public class XMLNotationDecl {

    //
    // Data
    //

    /** name */
    public String name;

    /** publicId */
    public String publicId;

    /** systemId */
    public String systemId;

    /** base systemId */
    public String baseSystemId;

    //
    // Methods
    //

    /**
     * setValues
     *
     * @param name
     * @param publicId
     * @param systemId
     */
    public void setValues(String name, String publicId, String systemId, String baseSystemId) {
        this.name     =   name;
        this.publicId = publicId;
        this.systemId = systemId;
        this.baseSystemId = baseSystemId;
    } // setValues

    /**
     * clear
     */
    public void clear() {
        this.name     = null;
        this.publicId = null;
        this.systemId = null;
        this.baseSystemId = null;
    } // clear

} // class XMLNotationDecl
