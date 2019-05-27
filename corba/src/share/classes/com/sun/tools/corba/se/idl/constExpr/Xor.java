package com.sun.tools.corba.se.idl.constExpr;

import com.sun.tools.corba.se.idl.Util;
import java.math.BigInteger;

public class Xor extends BinaryExpr
{
  protected Xor (Expression leftOperand, Expression rightOperand)
  {
    super ("^", leftOperand, rightOperand);
  } // ctor

  public Object evaluate () throws EvaluationException
  {
    try
    {
      Number l = (Number)left ().evaluate ();
      Number r = (Number)right ().evaluate ();

      if (l instanceof Float || l instanceof Double || r instanceof Float || r instanceof Double)
      {
        String[] parameters = {Util.getMessage ("EvaluationException.xor"), left ().value ().getClass ().getName (), right ().value ().getClass ().getName ()};
        throw new EvaluationException (Util.getMessage ("EvaluationException.1", parameters));
      }
      else
      {
        // Xor (^)
        //daz        value (new Long (l.longValue () ^ r.longValue ()));
        //BigInteger uL = (BigInteger)toUnsigned((BigInteger)l);
        //BigInteger uR = (BigInteger)toUnsigned((BigInteger)r);
        //value (coerceToTarget(uL.xor (uR)));
        BigInteger uL = (BigInteger)coerceToTarget((BigInteger)l);
        BigInteger uR = (BigInteger)coerceToTarget((BigInteger)r);
        value (uL.xor (uR));
      }
    }
    catch (ClassCastException e)
    {
      String[] parameters = {Util.getMessage ("EvaluationException.xor"), left ().value ().getClass ().getName (), right ().value ().getClass ().getName ()};
      throw new EvaluationException (Util.getMessage ("EvaluationException.1", parameters));
    }
    return value ();
  } // evaluate
} // class Xor
