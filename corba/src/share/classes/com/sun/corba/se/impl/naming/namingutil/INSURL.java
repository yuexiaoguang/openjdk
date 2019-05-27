package com.sun.corba.se.impl.naming.namingutil;

/**
 * INS URL is a generic interface for two different types of URL's specified
 * in INS spec.
 */
public interface INSURL {
    public boolean getRIRFlag( );

    // There can be one or more Endpoint's in the URL, so the return value is
    // a List
    public java.util.List getEndpointInfo( );

    public String getKeyString( );

    public String getStringifiedName( );

    // This method will return true only in CorbanameURL, It is provided because
    // corbaname: URL needs special handling.
    public boolean isCorbanameURL( );

    // A debug method, which is not required for normal operation
    public void dPrint( );
}
