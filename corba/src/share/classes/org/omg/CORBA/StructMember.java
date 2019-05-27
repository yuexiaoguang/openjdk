package org.omg.CORBA;

/**
 * Describes a member of an IDL <code>struct</code> in the
 * Interface Repository, including
 * the  name of the <code>struct</code> member, the type of
 * the <code>struct</code> member, and
 * the typedef that represents the IDL type of the
 * <code>struct</code> member
 * described the <code>struct</code> member object.
 */
public final class StructMember implements org.omg.CORBA.portable.IDLEntity {

    //  instance variables

    /**
     * The name of the struct member described by
     * this <code>StructMember</code> object.
     * @serial
     */
    public String name;

    /**
     * The type of the struct member described by
     * this <code>StructMember</code> object.
     * @serial
     */
    public org.omg.CORBA.TypeCode type;

    /**
     * The typedef that represents the IDL type of the struct member described by
     * this <code>StructMember</code> object.
     * @serial
     */
    public org.omg.CORBA.IDLType type_def;
    //  constructors

    /**
     * Constructs a default <code>StructMember</code> object.
     */
    public StructMember() { }

    /**
     * Constructs a <code>StructMember</code> object initialized with the
     * given values.
     * @param __name a <code>String</code> object with the name of the struct
     *        member
     * @param __type a <code>TypeCode</code> object describing the type of the struct
     *        member
     * @param __type_def an <code>IDLType</code> object representing the IDL type
     *        of the struct member
     */
    public StructMember(String __name, org.omg.CORBA.TypeCode __type, org.omg.CORBA.IDLType __type_def) {
        name = __name;
        type = __type;
        type_def = __type_def;
    }
}
