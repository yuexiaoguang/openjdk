/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.generic;

/**
 * DREM - Remainder of doubles
 * <PRE>Stack: ..., value1.word1, value1.word2, value2.word1, value2.word2 -&gt;</PRE>
 *        ..., result.word1, result.word2
 */
public class DREM extends ArithmeticInstruction {
  /** Remainder of doubles
   */
  public DREM() {
    super(com.sun.org.apache.bcel.internal.Constants.DREM);
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
    v.visitDREM(this);
  }
}
