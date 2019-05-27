package com.sun.org.apache.xerces.internal.xni;

/**
 * <p> This represents the basic physical description of the location of any
 * XML resource (a Schema grammar, a DTD, a general entity etc.) </p>
 */
public interface XMLResourceIdentifier {

    /** Sets the public identifier. */
    public void setPublicId(String publicId);

    /** Returns the public identifier. */
    public String getPublicId();

    /** Sets the expanded system identifier. */
    public void setExpandedSystemId(String systemId);

    /** Returns the expanded system identifier. */
    public String getExpandedSystemId();

    /** Sets the literal system identifier. */
    public void setLiteralSystemId(String systemId);

    /** Returns the literal system identifier. */
    public String getLiteralSystemId();

    /** Setsthe base URI against which the literal SystemId is to be
        resolved.*/
    public void setBaseSystemId(String systemId);

    /** <p> Returns the base URI against which the literal SystemId is to be
        resolved. </p> */
    public String getBaseSystemId();

    /** Sets the namespace of the resource. */
    public void setNamespace(String namespace);

    /** Returns the namespace of the resource. */
    public String getNamespace();

} // XMLResourceIdentifier
