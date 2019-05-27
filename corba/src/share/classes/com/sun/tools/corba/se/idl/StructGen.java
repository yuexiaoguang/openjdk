package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

import com.sun.tools.corba.se.idl.*;

public interface StructGen extends Generator
{
  void generate (Hashtable symbolTable, StructEntry entry, PrintWriter stream);
} // interface StructGen
