package com.sun.corba.se.impl.ior;

import com.sun.corba.se.spi.ior.Identifiable ;

import com.sun.corba.se.spi.orb.ORB ;

import com.sun.corba.se.impl.ior.IdentifiableFactoryFinderBase ;

import org.omg.CORBA_2_3.portable.InputStream ;

public class TaggedProfileFactoryFinderImpl extends
    IdentifiableFactoryFinderBase
{
    public TaggedProfileFactoryFinderImpl( ORB orb )
    {
        super( orb ) ;
    }

    public Identifiable handleMissingFactory( int id, InputStream is)
    {
        return new GenericTaggedProfile( id, is ) ;
    }
}
