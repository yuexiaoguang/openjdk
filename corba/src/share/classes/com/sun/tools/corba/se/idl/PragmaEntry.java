package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

/**
 * This is the symbol table entry for the #pragma statement.
 **/
public class PragmaEntry extends SymtabEntry
{
  protected PragmaEntry ()
  {
    super ();
    repositoryID (Util.emptyID);
  } // ctor

  protected PragmaEntry (SymtabEntry that)
  {
    super (that, new IDLID ());
    module (that.name ());
    name ("");
  } // ctor

  protected PragmaEntry (PragmaEntry that)
  {
    super (that);
  } // ctor

  public Object clone ()
  {
    return new PragmaEntry (this);
  } // clone

  /** Invoke the Include type generator.
      @param symbolTable the symbol table is a hash table whose key is
       a fully qualified type name and whose value is a SymtabEntry or
       a subclass of SymtabEntry.
      @param stream the stream to which the generator should sent its output.
      @see SymtabEntry */
  public void generate (Hashtable symbolTable, PrintWriter stream)
  {
    pragmaGen.generate (symbolTable, this, stream);
  } // generate

  /** Access the Include type generator.
      @returns an object which implements the IncludeGen interface.
      @see IncludeGen */
  public Generator generator ()
  {
    return pragmaGen;
  } // generator

  public String data ()
  {
    return _data;
  } // data

  public void data (String newData)
  {
    _data = newData;
  } // data

  static PragmaGen pragmaGen;

  private String _data = null;
} // class PragmaEntry
