/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.generic;

/**
 * DALOAD - Load double from array
 * <PRE>Stack: ..., arrayref, index -&gt; ..., result.word1, result.word2</PRE>
 */
public class DALOAD extends ArrayInstruction implements StackProducer {
  /** Load double from array
   */
  public DALOAD() {
    super(com.sun.org.apache.bcel.internal.Constants.DALOAD);
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
    v.visitStackProducer(this);
    v.visitExceptionThrower(this);
    v.visitTypedInstruction(this);
    v.visitArrayInstruction(this);
    v.visitDALOAD(this);
  }
}
