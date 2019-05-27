package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

public interface ConstGen extends Generator
{
  void generate (Hashtable symbolTable, ConstEntry entry, PrintWriter stream);
} // interface ConstGen
