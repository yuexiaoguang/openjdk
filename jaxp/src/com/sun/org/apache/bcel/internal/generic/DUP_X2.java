/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.generic;

/**
 * DUP_X2 - Duplicate top operand stack word and put three down
 * <PRE>Stack: ..., word3, word2, word1 -&gt; ..., word1, word3, word2, word1</PRE>
 */
public class DUP_X2 extends StackInstruction {
  public DUP_X2() {
    super(com.sun.org.apache.bcel.internal.Constants.DUP_X2);
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
    v.visitStackInstruction(this);
    v.visitDUP_X2(this);
  }
}
