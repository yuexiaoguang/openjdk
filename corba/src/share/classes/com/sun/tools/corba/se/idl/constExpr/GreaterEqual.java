package com.sun.tools.corba.se.idl.constExpr;

import com.sun.tools.corba.se.idl.Util;
import java.math.BigInteger;

public class GreaterEqual extends BinaryExpr
{
  protected GreaterEqual (Expression leftOperand, Expression rightOperand)
  {
    super (">=", leftOperand, rightOperand);
  } // ctor

  public Object evaluate () throws EvaluationException
  {
    try
    {
      Object left = left ().evaluate ();
      Object right = right ().evaluate ();
      if (left instanceof Boolean)
      {
        String[] parameters = {Util.getMessage ("EvaluationException.greaterEqual"), left ().value ().getClass ().getName (), right ().value ().getClass ().getName ()};
        throw new EvaluationException (Util.getMessage ("EvaluationException.1", parameters));
      }
      else
      {
        Number l = (Number)left;
        Number r = (Number)right ().evaluate ();
        if (l instanceof Float || l instanceof Double || r instanceof Float || r instanceof Double)
          value (new Boolean (l.doubleValue () >= r.doubleValue ()));
        else
          //daz          value (new Boolean (l.longValue () >= r.longValue ()));
          value (new Boolean ( ((BigInteger)l).compareTo ((BigInteger)r) >= 0));
      }
    }
    catch (ClassCastException e)
    {
      String[] parameters = {Util.getMessage ("EvaluationException.greaterEqual"), left ().value ().getClass ().getName (), right ().value ().getClass ().getName ()};
      throw new EvaluationException (Util.getMessage ("EvaluationException.1", parameters));
    }
    return value ();
  } // evaluate
} // class GreaterEqual
