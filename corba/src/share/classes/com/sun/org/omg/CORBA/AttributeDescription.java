package com.sun.org.omg.CORBA;


/**
* com/sun/org/omg/CORBA/AttributeDescription.java
* Generated by the IDL-to-Java compiler (portable), version "3.0"
* from ir.idl
*/
public final class AttributeDescription implements org.omg.CORBA.portable.IDLEntity
{
    public String name = null;
    public String id = null;
    public String defined_in = null;
    public String version = null;
    public org.omg.CORBA.TypeCode type = null;
    public com.sun.org.omg.CORBA.AttributeMode mode = null;

    public AttributeDescription ()
    {
    } // ctor

    public AttributeDescription (String _name, String _id, String _defined_in, String _version, org.omg.CORBA.TypeCode _type, com.sun.org.omg.CORBA.AttributeMode _mode)
    {
        name = _name;
        id = _id;
        defined_in = _defined_in;
        version = _version;
        type = _type;
        mode = _mode;
    } // ctor

} // class AttributeDescription
