/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.generic;

/**
 * DLOAD - Load double from local variable
 * <PRE>Stack ... -&gt; ..., result.word1, result.word2</PRE>
 */
public class DLOAD extends LoadInstruction {
  /**
   * Empty constructor needed for the Class.newInstance() statement in
   * Instruction.readInstruction(). Not to be used otherwise.
   */
  DLOAD() {
    super(com.sun.org.apache.bcel.internal.Constants.DLOAD, com.sun.org.apache.bcel.internal.Constants.DLOAD_0);
  }

  /** Load double from local variable
   * @param n index of local variable
   */
  public DLOAD(int n) {
    super(com.sun.org.apache.bcel.internal.Constants.DLOAD, com.sun.org.apache.bcel.internal.Constants.DLOAD_0, n);
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
    super.accept(v);
    v.visitDLOAD(this);
  }
}
