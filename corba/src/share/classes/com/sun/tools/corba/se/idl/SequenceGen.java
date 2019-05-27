package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

import com.sun.tools.corba.se.idl.*;

public interface SequenceGen extends Generator
{
  void generate (Hashtable symbolTable, SequenceEntry entry, PrintWriter stream);
} // interface SequenceGen
