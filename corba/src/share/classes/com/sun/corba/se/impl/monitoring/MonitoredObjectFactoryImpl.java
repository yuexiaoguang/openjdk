package com.sun.corba.se.impl.monitoring;

import com.sun.corba.se.spi.monitoring.MonitoredObjectFactory;
import com.sun.corba.se.spi.monitoring.MonitoredObject;

public class MonitoredObjectFactoryImpl implements MonitoredObjectFactory {

    public MonitoredObject createMonitoredObject( String name,
        String description )
    {
        return new MonitoredObjectImpl( name, description );
    }
}
