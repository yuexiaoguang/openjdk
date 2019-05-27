package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

import com.sun.tools.corba.se.idl.*;

public interface InterfaceGen extends Generator
{
  void generate (Hashtable symbolTable, InterfaceEntry entry, PrintWriter stream);
} // interface InterfaceGen
