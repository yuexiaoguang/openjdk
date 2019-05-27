package com.sun.tools.corba.se.idl.constExpr;

import com.sun.tools.corba.se.idl.Util;
import java.math.BigInteger;

public class ShiftRight extends BinaryExpr
{
  protected ShiftRight (Expression leftOperand, Expression rightOperand)
  {
    super (">>", leftOperand, rightOperand);
  } // ctor

  public Object evaluate () throws EvaluationException
  {
    try
    {
      Number l = (Number)left ().evaluate ();
      Number r = (Number)right ().evaluate ();

      if (l instanceof Float || l instanceof Double || r instanceof Float || r instanceof Double)
      {
        String[] parameters = {Util.getMessage ("EvaluationException.right"), left ().value ().getClass ().getName (), right ().value ().getClass ().getName ()};
        throw new EvaluationException (Util.getMessage ("EvaluationException.1", parameters));
      }
      else
      {
        // Shift right (>>)
        //daz        value (new Long (l.longValue () >> r.longValue ()));
        BigInteger bL = (BigInteger)coerceToTarget ((BigInteger)l);
        BigInteger bR = (BigInteger)r;

        // Change signed to unsigned (Clear sign bit--can be done when setting bL!)
        if (bL.signum () == -1)
          if (type ().equals ("short"))
            bL = bL.add (twoPow16);
          else if (type ().equals ("long"))
            bL = bL.add (twoPow32);
          else if (type ().equals ("long long"))
            bL = bL.add (twoPow64);

        value (bL.shiftRight (bR.intValue ()));
      }
    }
    catch (ClassCastException e)
    {
      String[] parameters = {Util.getMessage ("EvaluationException.right"), left ().value ().getClass ().getName (), right ().value ().getClass ().getName ()};
      throw new EvaluationException (Util.getMessage ("EvaluationException.1", parameters));
    }
    return value ();
  } // evaluate
} // class ShiftRight
