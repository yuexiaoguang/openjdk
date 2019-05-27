package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

import com.sun.tools.corba.se.idl.constExpr.Expression;

/**
 * This is the symbol table entry for constants.
 **/
public class ConstEntry extends SymtabEntry
{
  protected ConstEntry ()
  {
    super ();
  } // ctor

  protected ConstEntry (ConstEntry that)
  {
    super (that);
    if (module ().equals (""))
      module (name ());
    else if (!name ().equals (""))
      module (module () + "/" + name ());
    _value = that._value;
  } // ctor

  /** This is a shallow copy constructor. */
  protected ConstEntry (SymtabEntry that, IDLID clone)
  {
    super (that, clone);
    if (module ().equals (""))
      module (name ());
    else if (!name ().equals (""))
      module (module () + "/" + name ());
  } // ctor

  /** This is a shallow copy clone. */
  public Object clone ()
  {
    return new ConstEntry (this);
  } // clone

  /** Invoke the constant generator.
      @param symbolTable the symbol table is a hash table whose key is
       a fully qualified type name and whose value is a SymtabEntry or
       a subclass of SymtabEntry.
      @param stream the stream to which the generator should sent its output.
      @see SymtabEntry */
  public void generate (Hashtable symbolTable, PrintWriter stream)
  {
    constGen.generate (symbolTable, this, stream);
  } // generate

  /** Access the constant generator.
      @returns an object which implements the ConstGen interface.
      @see ConstGen */
  public Generator generator ()
  {
    return constGen;
  } // generator

  public Expression value ()
  {
    return _value;
  } // value

  public void value (Expression newValue)
  {
    _value = newValue;
  } // value

  static ConstGen    constGen;
  private Expression _value = null;
} // class ConstEntry
