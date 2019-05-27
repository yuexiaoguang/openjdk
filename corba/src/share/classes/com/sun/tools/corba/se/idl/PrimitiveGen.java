package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

import com.sun.tools.corba.se.idl.*;

public interface PrimitiveGen extends Generator
{
  void generate (Hashtable symbolTable, PrimitiveEntry entry, PrintWriter stream);
} // interface PrimitiveGen
