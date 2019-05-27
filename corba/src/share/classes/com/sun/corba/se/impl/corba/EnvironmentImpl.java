package com.sun.corba.se.impl.corba;

import org.omg.CORBA.Environment;
import org.omg.CORBA.UserException;
import org.omg.CORBA.ORB;

public class EnvironmentImpl extends Environment {

    private Exception _exc;

    public EnvironmentImpl()
    {
    }

    public Exception exception()
    {
        return _exc;
    }

    public void exception(Exception exc)
    {
        _exc = exc;
    }

    public void clear()
    {
        _exc = null;
    }

}
