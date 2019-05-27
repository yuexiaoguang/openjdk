package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/**
 * This is the symbol table entry for forward declarations of values.
 **/
public class ForwardValueEntry extends ForwardEntry
{
  protected ForwardValueEntry ()
  {
    super ();
  } // ctor

  protected ForwardValueEntry (ForwardValueEntry that)
  {
    super (that);
  } // ctor

  protected ForwardValueEntry (SymtabEntry that, IDLID clone)
  {
    super (that, clone);
  } // ctor

  public Object clone ()
  {
    return new ForwardValueEntry (this);
  } // clone

  /** Invoke the forward value declaration generator.
      @param symbolTable the symbol table is a hash table whose key is
       a fully qualified type name and whose value is a SymtabEntry or
       a subclass of SymtabEntry.
      @param stream the stream to which the generator should sent its output.
      @see SymtabEntry */
  public void generate (Hashtable symbolTable, PrintWriter stream)
  {
    forwardValueGen.generate (symbolTable, this, stream);
  } // generate

  /** Access the interface generator.
      @returns an object which implements the ForwardValueGen interface.
      @see ValueGen */
  public Generator generator ()
  {
     return forwardValueGen;
  } // generator

  static ForwardValueGen forwardValueGen;
} // class ForwardValueEntry
