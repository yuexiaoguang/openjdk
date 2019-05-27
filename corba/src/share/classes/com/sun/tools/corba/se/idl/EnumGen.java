package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

import com.sun.tools.corba.se.idl.*;

public interface EnumGen extends Generator
{
  void generate (Hashtable symbolTable, EnumEntry entry, PrintWriter stream);
} // interface EnumGen
