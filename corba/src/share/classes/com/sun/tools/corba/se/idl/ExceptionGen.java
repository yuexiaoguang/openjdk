package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

import com.sun.tools.corba.se.idl.*;

public interface ExceptionGen extends Generator
{
  void generate (Hashtable symbolTable, ExceptionEntry entry, PrintWriter stream);
} // interface ExceptionGen
