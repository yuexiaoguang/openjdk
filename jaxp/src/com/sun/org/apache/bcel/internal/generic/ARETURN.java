/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.generic;

/**
 * ARETURN -  Return reference from method
 * <PRE>Stack: ..., objectref -&gt; &lt;empty&gt;</PRE>
 */
public class ARETURN extends ReturnInstruction {
  /**
   * Return reference from method
   */
  public ARETURN() {
    super(com.sun.org.apache.bcel.internal.Constants.ARETURN);
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
    v.visitExceptionThrower(this);
    v.visitTypedInstruction(this);
    v.visitStackConsumer(this);
    v.visitReturnInstruction(this);
    v.visitARETURN(this);
  }
}
