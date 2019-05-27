package com.sun.tools.corba.se.idl.toJavaPortable;

import java.io.PrintWriter;

import com.sun.tools.corba.se.idl.SymtabEntry;

public interface JavaGenerator
{
  // The helper methods print the specific helper method.
  // The helper read/write methods call the read/write methods.

  int helperType (int index, String indent, TCOffsets tcoffsets, String name, SymtabEntry entry, PrintWriter stream);

  void helperRead (String entryName, SymtabEntry entry, PrintWriter stream);

  void helperWrite (SymtabEntry entry, PrintWriter stream);

  // The read/write methods print the streaming of the type.
  // This printed code is found in the helper method but it is only
  // that code that is concerned with streaming itself.

  int read (int index, String indent, String name, SymtabEntry entry, PrintWriter stream);

  int write (int index, String indent, String name, SymtabEntry entry, PrintWriter stream);

  int type (int index, String indent, TCOffsets tcoffsets, String name, SymtabEntry entry, PrintWriter stream);

} // interface JavaGenerator
