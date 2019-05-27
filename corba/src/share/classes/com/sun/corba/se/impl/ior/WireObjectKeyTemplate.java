package com.sun.corba.se.impl.ior;

import org.omg.CORBA.OctetSeqHolder ;

import org.omg.CORBA_2_3.portable.OutputStream ;
import org.omg.CORBA_2_3.portable.InputStream ;

import com.sun.corba.se.spi.protocol.CorbaServerRequestDispatcher ;

import com.sun.corba.se.spi.ior.ObjectKeyTemplate ;
import com.sun.corba.se.spi.ior.ObjectId ;
import com.sun.corba.se.spi.ior.ObjectAdapterId ;

import com.sun.corba.se.spi.orb.ORB ;
import com.sun.corba.se.spi.orb.ORBVersion ;
import com.sun.corba.se.spi.orb.ORBVersionFactory ;

import com.sun.corba.se.spi.logging.CORBALogDomains ;

import com.sun.corba.se.impl.orbutil.ORBConstants ;

import com.sun.corba.se.impl.encoding.CDRInputStream ;

import com.sun.corba.se.impl.logging.IORSystemException ;

public class WireObjectKeyTemplate implements ObjectKeyTemplate
{
    private ORB orb ;
    private IORSystemException wrapper ;

    public boolean equals( Object obj )
    {
        if (obj == null)
            return false ;

        return obj instanceof WireObjectKeyTemplate ;
    }

    public int hashCode()
    {
        return 53 ; // All WireObjectKeyTemplates are the same, so they should
                    // have the same hashCode.
    }

    private byte[] getId( InputStream is )
    {
        CDRInputStream cis = (CDRInputStream)is ;
        int len = cis.getBufferLength() ;
        byte[] result = new byte[ len ] ;
        cis.read_octet_array( result, 0, len ) ;
        return result ;
    }

    public WireObjectKeyTemplate( ORB orb )
    {
        initORB( orb ) ;
    }

    public WireObjectKeyTemplate( InputStream is, OctetSeqHolder osh )
    {
        osh.value = getId( is ) ;
        initORB( (ORB)(is.orb())) ;
    }

    private void initORB( ORB orb )
    {
        this.orb = orb ;
        wrapper = IORSystemException.get( orb,
            CORBALogDomains.OA_IOR ) ;
    }

    public void write( ObjectId id, OutputStream os )
    {
        byte[] key = id.getId() ;
        os.write_octet_array( key, 0, key.length ) ;
    }

    public void write( OutputStream os )
    {
        // Does nothing
    }

    public int getSubcontractId()
    {
        return ORBConstants.DEFAULT_SCID ;
    }

    /** While it might make sense to throw an exception here, this causes
    * problems since we need to check whether unusual object references
    * are local or not.  It seems that the easiest way to handle this is
    * to return an invalid server id.
    */
    public int getServerId()
    {
        return -1 ;
    }

    public String getORBId()
    {
        throw wrapper.orbIdNotAvailable() ;
    }

    public ObjectAdapterId getObjectAdapterId()
    {
        throw wrapper.objectAdapterIdNotAvailable() ;
    }

    /** Adapter ID is not available, since our
    * ORB did not implement the object carrying this key.
    */
    public byte[] getAdapterId()
    {
        throw wrapper.adapterIdNotAvailable() ;
    }

    public ORBVersion getORBVersion()
    {
        return ORBVersionFactory.getFOREIGN() ;
    }

    public CorbaServerRequestDispatcher getServerRequestDispatcher( ORB orb, ObjectId id )
    {
        byte[] bid = id.getId() ;
        String str = new String( bid ) ;
        return orb.getRequestDispatcherRegistry().getServerRequestDispatcher( str ) ;
    }
}
