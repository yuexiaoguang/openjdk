package com.sun.tools.corba.se.idl.constExpr;

import com.sun.tools.corba.se.idl.Util;
import java.math.BigInteger;

public class BooleanNot extends UnaryExpr
{
  protected BooleanNot (Expression operand)
  {
    super ("!", operand);
  } // ctor

  public Object evaluate () throws EvaluationException
  {
    try
    {
      Object tmp = operand ().evaluate ();
      Boolean op;
      //daz      if (tmp instanceof Number)
      //           op = new Boolean (((Number)tmp).longValue () != 0);
      //         else
      //           op = (Boolean)tmp;
      if (tmp instanceof Number)
      {
        if (tmp instanceof BigInteger)
          op = new Boolean (((BigInteger)tmp).compareTo (zero) != 0);
        else
          op = new Boolean (((Number)tmp).longValue () != 0);
      }
      else
        op = (Boolean)tmp;

      value (new Boolean (!op.booleanValue ()));
    }
    catch (ClassCastException e)
    {
      String[] parameters = {Util.getMessage ("EvaluationException.booleanNot"), operand ().value ().getClass ().getName ()};
      throw new EvaluationException (Util.getMessage ("EvaluationException.2", parameters));
    }
    return value ();
  } // evaluate
} // class BooleanNot
