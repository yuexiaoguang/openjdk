package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

import com.sun.tools.corba.se.idl.*;

public interface ParameterGen extends Generator
{
  void generate (Hashtable symbolTable, ParameterEntry entry, PrintWriter stream);
} // interface ParameterGen
