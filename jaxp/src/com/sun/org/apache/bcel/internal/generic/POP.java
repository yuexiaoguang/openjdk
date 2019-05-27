/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.generic;

/**
 * POP - Pop top operand stack word
 *
 * <PRE>Stack: ..., word -&gt; ...</PRE>
 */
public class POP extends StackInstruction implements PopInstruction {
  public POP() {
    super(com.sun.org.apache.bcel.internal.Constants.POP);
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
    v.visitPopInstruction(this);
    v.visitStackInstruction(this);
    v.visitPOP(this);
  }
}
