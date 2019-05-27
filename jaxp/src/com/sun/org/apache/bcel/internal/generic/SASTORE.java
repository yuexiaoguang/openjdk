/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.generic;

/**
 * SASTORE - Store into short array
 * <PRE>Stack: ..., arrayref, index, value -&gt; ...</PRE>
 */
public class SASTORE extends ArrayInstruction implements StackConsumer {
  public SASTORE() {
    super(com.sun.org.apache.bcel.internal.Constants.SASTORE);
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
    v.visitStackConsumer(this);
    v.visitExceptionThrower(this);
    v.visitTypedInstruction(this);
    v.visitArrayInstruction(this);
    v.visitSASTORE(this);
  }
}
