package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

import com.sun.tools.corba.se.idl.*;

public interface ForwardGen extends Generator
{
  void generate (Hashtable symbolTable, ForwardEntry entry, PrintWriter stream);
} // interface ForwardGen
