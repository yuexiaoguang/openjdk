package org.omg.CORBA;
/**
An IRObject IDL interface represents the most generic interface
from which all other Interface Repository interfaces are derived,
even the Repository itself.
*/
public interface IRObject extends IRObjectOperations, org.omg.CORBA.Object,
    org.omg.CORBA.portable.IDLEntity
{
}
