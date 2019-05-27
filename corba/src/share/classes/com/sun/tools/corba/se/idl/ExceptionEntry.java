package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/**
 * This is the symbol table entry for exceptions.  An exception is simply
 * a struct by a different name.
 **/
public class ExceptionEntry extends StructEntry
{
  protected ExceptionEntry ()
  {
    super ();
  } // ctor

  protected ExceptionEntry (ExceptionEntry that)
  {
    super (that);
  } // ctor

  protected ExceptionEntry (SymtabEntry that, IDLID clone)
  {
    super (that, clone);
   } // ctor

  public Object clone ()
  {
    return new ExceptionEntry (this);
  } // clone

  /** Invoke the exception generator.
      @param symbolTable the symbol table is a hash table whose key is
       a fully qualified type name and whose value is a SymtabEntry or
       a subclass of SymtabEntry.
      @param stream the stream to which the generator should sent its output.
      @see SymtabEntry */
  public void generate (Hashtable symbolTable, PrintWriter stream)
  {
    exceptionGen.generate (symbolTable, this, stream);
  } // generate

  /** Access the exception generator.
      @returns an object which implements the ExceptionGen interface.
      @see ExceptionGen */
  public Generator generator ()
  {
    return exceptionGen;
  } // generator

  static ExceptionGen exceptionGen;
} // class ExceptionEntry
