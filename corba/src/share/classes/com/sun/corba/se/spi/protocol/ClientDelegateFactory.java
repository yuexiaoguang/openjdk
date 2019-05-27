package com.sun.corba.se.spi.protocol ;

import com.sun.corba.se.spi.transport.CorbaContactInfoList ;

import com.sun.corba.se.spi.protocol.CorbaClientDelegate ;

/** Interface used to create a ClientDelegate from a ContactInfoList.
 */
public interface ClientDelegateFactory {
    CorbaClientDelegate create( CorbaContactInfoList list ) ;
}
