/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.generic;

/**
 * CALOAD - Load char from array
 * <PRE>Stack: ..., arrayref, index -&gt; ..., value</PRE>
 */
public class CALOAD extends ArrayInstruction implements StackProducer {
  /** Load char from array
   */
  public CALOAD() {
    super(com.sun.org.apache.bcel.internal.Constants.CALOAD);
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
    v.visitCALOAD(this);
  }
}
