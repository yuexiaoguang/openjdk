package com.sun.corba.se.impl.corba;

import org.omg.CORBA.NamedValue;
import org.omg.CORBA.Any;

import com.sun.corba.se.spi.orb.ORB ;

public class NamedValueImpl extends NamedValue
{
    private String _name;
    private Any    _value;
    private int    _flags;
    private ORB    _orb;

    public NamedValueImpl(ORB orb)
    {
        // Note: This orb could be an instanceof ORBSingleton or ORB
        _orb = orb;
        _value = new AnyImpl(_orb);
    }

    public NamedValueImpl(ORB orb,
                          String name,
                          Any value,
                          int flags)
    {
        // Note: This orb could be an instanceof ORBSingleton or ORB
        _orb    = orb;
        _name   = name;
        _value  = value;
        _flags      = flags;
    }

    public String name()
    {
        return _name;
    }

    public Any value()
    {
        return _value;
    }

    public int flags()
    {
        return _flags;
    }
}
