package com.sun.corba.se.spi.transport;

import com.sun.corba.se.spi.ior.IOR ;

import com.sun.corba.se.spi.protocol.LocalClientRequestDispatcher ;

import com.sun.corba.se.pept.transport.ContactInfoList ;

public interface CorbaContactInfoList
    extends
        ContactInfoList
{
    public void setTargetIOR(IOR ior);
    public IOR getTargetIOR();

    public void setEffectiveTargetIOR(IOR locatedIor);
    public IOR getEffectiveTargetIOR();

    public LocalClientRequestDispatcher getLocalClientRequestDispatcher();

    public int hashCode();
}
