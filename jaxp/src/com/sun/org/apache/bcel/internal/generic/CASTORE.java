/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.generic;

/**
 * CASTORE -  Store into char array
 * <PRE>Stack: ..., arrayref, index, value -&gt; ...</PRE>
 */
public class CASTORE extends ArrayInstruction implements StackConsumer {
  /** Store char into array
   */
  public CASTORE() {
    super(com.sun.org.apache.bcel.internal.Constants.CASTORE);
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
    v.visitCASTORE(this);
  }
}
