package com.sun.corba.se.spi.ior;

public interface TaggedComponentFactoryFinder extends IdentifiableFactoryFinder
{
    /** Create a tagged component from a GIOP marshalled representation
     * of a tagged component.  This is needed for portable interceptors.
     */
    TaggedComponent create( org.omg.CORBA.ORB orb,
        org.omg.IOP.TaggedComponent comp ) ;
}
