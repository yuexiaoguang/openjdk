package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;
import com.sun.tools.corba.se.idl.*;

public interface ValueGen extends Generator {
   void generate ( Hashtable symbolTable, ValueEntry entry, PrintWriter stream);
} // interface ValueGen
