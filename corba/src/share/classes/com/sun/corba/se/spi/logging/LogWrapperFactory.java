package com.sun.corba.se.spi.logging ;

import java.util.logging.Logger ;

import com.sun.corba.se.spi.logging.LogWrapperBase ;

public interface LogWrapperFactory {
    LogWrapperBase create( Logger logger ) ;
}
