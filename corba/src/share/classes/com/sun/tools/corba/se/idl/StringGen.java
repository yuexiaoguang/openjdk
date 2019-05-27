package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

import com.sun.tools.corba.se.idl.*;

public interface StringGen extends Generator
{
  void generate (Hashtable symbolTable, StringEntry entry, PrintWriter stream);
} // interface StringGen
