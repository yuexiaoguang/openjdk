package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

import com.sun.tools.corba.se.idl.*;

public interface MethodGen extends Generator
{
  void generate (Hashtable symbolTable, MethodEntry entry, PrintWriter stream);
} // interface MethodGen
