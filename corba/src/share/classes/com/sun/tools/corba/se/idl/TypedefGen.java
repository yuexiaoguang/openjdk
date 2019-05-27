package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

import com.sun.tools.corba.se.idl.*;

public interface TypedefGen extends Generator
{
  void generate (Hashtable symbolTable, TypedefEntry entry, PrintWriter stream);
} // interface TypedefGen
