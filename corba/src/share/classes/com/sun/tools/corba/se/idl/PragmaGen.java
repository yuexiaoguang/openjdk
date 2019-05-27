package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

import com.sun.tools.corba.se.idl.*;

public interface PragmaGen extends Generator
{
  void generate (Hashtable symbolTable, PragmaEntry entry, PrintWriter stream);
} // interface PragmaGen
