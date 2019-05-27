package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

import com.sun.tools.corba.se.idl.*;

public interface ForwardValueGen extends Generator
{
  void generate (Hashtable symbolTable, ForwardValueEntry entry, PrintWriter stream);
} // interface ForwardValueGen
