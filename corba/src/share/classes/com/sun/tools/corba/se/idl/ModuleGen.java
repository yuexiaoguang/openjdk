package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

import com.sun.tools.corba.se.idl.*;

public interface ModuleGen extends Generator
{
  void generate (Hashtable symbolTable, ModuleEntry entry, PrintWriter stream);
} // interface ModuleGen
