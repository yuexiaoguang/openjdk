/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.generic;

/**
 * F2L - Convert float to long
 * <PRE>Stack: ..., value -&gt; ..., result.word1, result.word2</PRE>
 */
public class F2L extends ConversionInstruction {
  /** Convert float to long
   */
  public F2L() {
    super(com.sun.org.apache.bcel.internal.Constants.F2L);
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
    v.visitConversionInstruction(this);
    v.visitF2L(this);
  }
}
