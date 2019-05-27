/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.generic;

/**
 * ALOAD - Load reference from local variable
 * <PRE>Stack: ... -&gt; ..., objectref</PRE>
 */
public class ALOAD extends LoadInstruction {
  /**
   * Empty constructor needed for the Class.newInstance() statement in
   * Instruction.readInstruction(). Not to be used otherwise.
   */
  ALOAD() {
    super(com.sun.org.apache.bcel.internal.Constants.ALOAD, com.sun.org.apache.bcel.internal.Constants.ALOAD_0);
  }

  /** Load reference from local variable
   * @param n index of local variable
   */
  public ALOAD(int n) {
    super(com.sun.org.apache.bcel.internal.Constants.ALOAD, com.sun.org.apache.bcel.internal.Constants.ALOAD_0, n);
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
    v.visitALOAD(this);
  }
}
