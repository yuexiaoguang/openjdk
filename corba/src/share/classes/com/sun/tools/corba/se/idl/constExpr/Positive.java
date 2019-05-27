package com.sun.tools.corba.se.idl.constExpr;

import com.sun.tools.corba.se.idl.Util;
import java.math.BigInteger;

public class Positive extends UnaryExpr
{
  protected Positive (Expression operand)
  {
    super ("+", operand);
  } // ctor

  public Object evaluate () throws EvaluationException
  {
    try
    {
      Number op = (Number)operand ().evaluate ();

      if (op instanceof Float || op instanceof Double)
        value (new Double (+op.doubleValue ()));
      else
      {
        // Multiply by sign
        //daz        value (new Long (+op.longValue ()));
        value (((BigInteger)op).multiply (BigInteger.valueOf (((BigInteger)op).signum ())));
        //promote ();
      }
    }
    catch (ClassCastException e)
    {
      String[] parameters = {Util.getMessage ("EvaluationException.pos"), operand ().value ().getClass ().getName ()};
      throw new EvaluationException (Util.getMessage ("EvaluationException.2", parameters));
    }
    return value ();
  } // evaluate
} // class Positive
