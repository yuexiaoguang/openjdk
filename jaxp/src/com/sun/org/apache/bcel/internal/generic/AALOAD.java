/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.generic;

/**
 * AALOAD - Load reference from array
 * <PRE>Stack: ..., arrayref, index -&gt; value</PRE>
 */
public class AALOAD extends ArrayInstruction implements StackProducer {
  /** Load reference from array
   */
  public AALOAD() {
    super(com.sun.org.apache.bcel.internal.Constants.AALOAD);
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
    v.visitAALOAD(this);
  }
}
