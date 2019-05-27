package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;
import com.sun.tools.corba.se.idl.*;

public interface ValueBoxGen extends Generator
{
  void generate (Hashtable symbolTable, ValueBoxEntry entry, PrintWriter stream);
} // interface ValueBoxGen
