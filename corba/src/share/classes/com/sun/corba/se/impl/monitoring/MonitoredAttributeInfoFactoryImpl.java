package com.sun.corba.se.impl.monitoring;

import com.sun.corba.se.spi.monitoring.MonitoredAttributeInfoFactory;
import com.sun.corba.se.spi.monitoring.MonitoredAttributeInfo;

public class MonitoredAttributeInfoFactoryImpl
    implements MonitoredAttributeInfoFactory
{
    public MonitoredAttributeInfo createMonitoredAttributeInfo(
        String description, Class type, boolean isWritable,
        boolean isStatistic  )
    {
        return new MonitoredAttributeInfoImpl( description, type,
            isWritable, isStatistic );
    }
}
