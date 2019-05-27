package org.omg.CORBA;

/** Defines the code used to represent a truncatable value type in
* a typecode. A value type is truncatable if it inherits "safely"
* from another value type, which means it can be cast to a more
* general inherited type.
* This is one of the possible results of the <code>type_modifier</code>
* method on the <code>TypeCode</code> interface.
*/
public interface VM_TRUNCATABLE {
    /** The value representing a truncatable value type in
    * a typecode.
    */
    final short value = (short) (3L);
}
