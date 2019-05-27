package com.sun.corba.se.spi.oa ;

import com.sun.corba.se.impl.oa.poa.POAFactory ;
import com.sun.corba.se.impl.oa.toa.TOAFactory ;
import com.sun.corba.se.spi.orb.ORB ;

/** OADefault provides methods to create the standard ObjectAdapterFactory
 * instances for this version of the ORB.  These methods are generally
 * used in ORBConfigurator instances to construct an ORB instance.
 */
public class OADefault {
    public static ObjectAdapterFactory makePOAFactory( ORB orb )
    {
        ObjectAdapterFactory oaf = new POAFactory() ;
        oaf.init( orb ) ;
        return oaf ;
    }

    public static ObjectAdapterFactory makeTOAFactory( ORB orb )
    {
        ObjectAdapterFactory oaf = new TOAFactory() ;
        oaf.init( orb ) ;
        return oaf ;
    }
}
