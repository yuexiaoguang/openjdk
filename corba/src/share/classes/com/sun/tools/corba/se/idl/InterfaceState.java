package com.sun.tools.corba.se.idl;

/**
 * This class is only used within an InterfaceEntry.  If the interface
 * is stateful, then its state vector will contain one or more of these
 * InterfaceStates.
 **/
public class InterfaceState
{
  public static final int Private   = 0,
                          Protected = 1,
                          Public    = 2;

  public InterfaceState (int m, TypedefEntry e)
  {
    modifier = m;
    entry    = e;
    if (modifier < Private || modifier > Public)
      modifier = Public;
  } // ctor

  public int          modifier = Public;
  public TypedefEntry entry    = null;
} // class InterfaceState
