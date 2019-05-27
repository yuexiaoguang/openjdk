/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.generic;

/**
 * FLOAD - Load float from local variable
 * <PRE>Stack ... -&gt; ..., result</PRE>
 */
public class FLOAD extends LoadInstruction {
  /**
   * Empty constructor needed for the Class.newInstance() statement in
   * Instruction.readInstruction(). Not to be used otherwise.
   */
  FLOAD() {
    super(com.sun.org.apache.bcel.internal.Constants.FLOAD, com.sun.org.apache.bcel.internal.Constants.FLOAD_0);
  }

  /** Load float from local variable
   * @param n index of local variable
   */
  public FLOAD(int n) {
    super(com.sun.org.apache.bcel.internal.Constants.FLOAD, com.sun.org.apache.bcel.internal.Constants.FLOAD_0, n);
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
    v.visitFLOAD(this);
  }
}
