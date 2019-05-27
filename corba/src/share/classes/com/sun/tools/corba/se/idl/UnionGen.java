package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

import com.sun.tools.corba.se.idl.*;

public interface UnionGen extends Generator
{
  void generate (Hashtable symtab, UnionEntry entry, PrintWriter stream);
} // interface UnionGen
