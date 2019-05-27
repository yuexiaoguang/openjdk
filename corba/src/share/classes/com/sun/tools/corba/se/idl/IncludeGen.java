package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

import com.sun.tools.corba.se.idl.*;

public interface IncludeGen extends Generator
{
  void generate (Hashtable symbolTable, IncludeEntry entry, PrintWriter stream);
} // interface IncludeGen
