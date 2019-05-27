package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Vector;

/**
 * This is the symbol table entry for enumerated types.
 **/
public class EnumEntry extends SymtabEntry
{
  protected EnumEntry ()
  {
    super ();
  } // ctor

  protected EnumEntry (EnumEntry that)
  {
    super (that);
    _elements = (Vector)that._elements.clone ();
  } // ctor

  protected EnumEntry (SymtabEntry that, IDLID clone)
  {
    super (that, clone);

    if (module ().equals (""))
      module (name ());
    else if (!name ().equals (""))
      module (module () + "/" + name ());
  } // ctor

  public Object clone ()
  {
    return new EnumEntry (this);
  } // clone

  /** Invoke the enumerator generator.
      @param symbolTable the symbol table is a hash table whose key is
       a fully qualified type name and whose value is a SymtabEntry or
       a subclass of SymtabEntry.
      @param stream the stream to which the generator should sent its output.
      @see SymtabEntry */
  public void generate (Hashtable symbolTable, PrintWriter stream)
  {
    enumGen.generate (symbolTable, this, stream);
  } // generate

  /** Access the enumerator generator.
      @returns an object which implements the EnumGen interface.
      @see EnumGen */
  public Generator generator ()
  {
    return enumGen;
  } // generator

  /** Add an element to the list of elements. */
  public void addElement (String element)
  {
    _elements.addElement (element);
  } // addElement

  /** Each element of the vector is a String. */
  public Vector elements ()
  {
    return _elements;
  } // elements

  static  EnumGen enumGen;
  private Vector  _elements = new Vector ();
} // class EnumEntry
