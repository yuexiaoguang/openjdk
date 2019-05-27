package com.sun.corba.se.spi.transport;

import com.sun.corba.se.pept.transport.Acceptor;

import com.sun.corba.se.spi.ior.IORTemplate;

// REVISIT - impl/poa specific:
import com.sun.corba.se.impl.oa.poa.Policies;

public interface CorbaAcceptor
    extends
        Acceptor
{
    public String getObjectAdapterId();
    public String getObjectAdapterManagerId();
    public void addToIORTemplate(IORTemplate iorTemplate, Policies policies,
                                 String codebase);
    public String getMonitoringName();
}
