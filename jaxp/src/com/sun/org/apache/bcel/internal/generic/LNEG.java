/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.generic;

/**
 * LNEG - Negate long
 * <PRE>Stack: ..., value.word1, value.word2 -&gt; ..., result.word1, result.word2</PRE>
 */
public class LNEG extends ArithmeticInstruction {
  public LNEG() {
    super(com.sun.org.apache.bcel.internal.Constants.LNEG);
  }


  /**
   * Call corresponding visitor method(s). The order is:
   * Call visitor methods of implemented interfaces first, then
   * call methods according to the class hierarchy in descending order,
   * i.e., the most specific visitXXX() call comes last.
   *
   * @param v Visitor object
   */
  public void accept(Visitor v) {
    v.visitTypedInstruction(this);
    v.visitStackProducer(this);
    v.visitStackConsumer(this);
    v.visitArithmeticInstruction(this);
    v.visitLNEG(this);
  }
}
