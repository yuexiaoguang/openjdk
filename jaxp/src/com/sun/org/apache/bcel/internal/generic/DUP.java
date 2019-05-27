/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.generic;

/**
 * DUP - Duplicate top operand stack word
 * <PRE>Stack: ..., word -&gt; ..., word, word</PRE>
 */
public class DUP extends StackInstruction implements PushInstruction {
  public DUP() {
    super(com.sun.org.apache.bcel.internal.Constants.DUP);
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
    v.visitPushInstruction(this);
    v.visitStackInstruction(this);
    v.visitDUP(this);
  }
}
