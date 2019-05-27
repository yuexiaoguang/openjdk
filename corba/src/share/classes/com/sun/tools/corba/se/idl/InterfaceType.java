package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

import com.sun.tools.corba.se.idl.*;

public interface InterfaceType
{
  public static final int NORMAL = 0 ;
  public static final int ABSTRACT = 1 ;

  // LOCAL generates code according to the Local interfaces
  // Here helper and holder classes are the only ones generated
  public static final int LOCAL = 2 ;

  // intermediate solution to enable people to extend ServantLocatorPOA
  // and ServantActivatorPOA for interoperability.  This is until the
  // POA is declared Local officially by OMG
  public static final int LOCALSERVANT = 3 ;

  // generate only signature interfaces, no helper/holders are generated
  public static final int LOCAL_SIGNATURE_ONLY = 4 ;

  public int getInterfaceType ();

  public void setInterfaceType( int type ) ;
} // interface InterfaceType
