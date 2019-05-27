/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.generic;

/**
 * LREM - Remainder of long
 * <PRE>Stack: ..., value1, value2 -&gt; result</PRE>
 */
public class LREM extends ArithmeticInstruction implements ExceptionThrower {
  public LREM() {
    super(com.sun.org.apache.bcel.internal.Constants.LREM);
  }

  public Class[] getExceptions() { return new Class[] { com.sun.org.apache.bcel.internal.ExceptionConstants.ARITHMETIC_EXCEPTION }; }


  /**
   * Call corresponding visitor method(s). The order is:
   * Call visitor methods of implemented interfaces first, then
   * call methods according to the class hierarchy in descending order,
   * i.e., the most specific visitXXX() call comes last.
   *
   * @param v Visitor object
   */
  public void accept(Visitor v) {
    v.visitExceptionThrower(this);
    v.visitTypedInstruction(this);
    v.visitStackProducer(this);
    v.visitStackConsumer(this);
    v.visitArithmeticInstruction(this);
    v.visitLREM(this);
  }
}
