package com.sun.corba.se.impl.copyobject ;

import com.sun.corba.se.spi.copyobject.ObjectCopier ;

public class ReferenceObjectCopierImpl implements ObjectCopier
{
    public Object copy( Object obj )
    {
        return obj ;
    }
}
