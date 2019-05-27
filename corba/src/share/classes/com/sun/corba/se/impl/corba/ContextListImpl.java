package com.sun.corba.se.impl.corba;

import java.util.Vector;
import org.omg.CORBA.ContextList;
import org.omg.CORBA.Bounds;
import org.omg.CORBA.ORB;

public class ContextListImpl extends ContextList
{
    private final int    INITIAL_CAPACITY       = 2;
    private final int    CAPACITY_INCREMENT     = 2;

    private org.omg.CORBA.ORB _orb;
    private Vector _contexts;

    public ContextListImpl(org.omg.CORBA.ORB orb)
    {
        // Note: This orb could be an instanceof ORBSingleton or ORB
        _orb = orb;
        _contexts = new Vector(INITIAL_CAPACITY, CAPACITY_INCREMENT);
    }

    public int count()
    {
        return _contexts.size();
    }

    public void add(String ctxt)
    {
        _contexts.addElement(ctxt);
    }

    public String item(int index)
        throws Bounds
    {
        try {
            return (String) _contexts.elementAt(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new Bounds();
        }
    }

    public void remove(int index)
        throws Bounds
    {
        try {
            _contexts.removeElementAt(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new Bounds();
        }
    }

}
