package com.sun.corba.se.spi.protocol;

import com.sun.corba.se.spi.ior.IOR ;

public interface LocalClientRequestDispatcherFactory {
    public LocalClientRequestDispatcher create( int id, IOR ior )  ;
}
