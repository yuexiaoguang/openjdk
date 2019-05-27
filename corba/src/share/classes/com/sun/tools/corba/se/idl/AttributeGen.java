package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

public interface AttributeGen extends Generator
{
  void generate (Hashtable symbolTable, AttributeEntry entry, PrintWriter stream);
} // interface AttributeGen
