package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

public interface NativeGen extends Generator
{
  void generate (Hashtable symbolTable, NativeEntry entry, PrintWriter stream);
} // interface NativeGen
