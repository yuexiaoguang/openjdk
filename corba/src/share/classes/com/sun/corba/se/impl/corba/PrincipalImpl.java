package com.sun.corba.se.impl.corba;


public class PrincipalImpl extends org.omg.CORBA.Principal
{
    private byte[] value;

    public void name(byte[] value)
    {
        this.value = value;
    }

    public byte[] name()
    {
        return value;
    }
}
