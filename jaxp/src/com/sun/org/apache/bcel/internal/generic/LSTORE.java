/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.generic;

/**
 * LSTORE - Store long into local variable
 * <PRE>Stack: ..., value.word1, value.word2 -&gt; ... </PRE>
 */
public class LSTORE extends StoreInstruction {
  /**
   * Empty constructor needed for the Class.newInstance() statement in
   * Instruction.readInstruction(). Not to be used otherwise.
   */
  LSTORE() {
    super(com.sun.org.apache.bcel.internal.Constants.LSTORE, com.sun.org.apache.bcel.internal.Constants.LSTORE_0);
  }

  public LSTORE(int n) {
    super(com.sun.org.apache.bcel.internal.Constants.LSTORE, com.sun.org.apache.bcel.internal.Constants.LSTORE_0, n);
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
    v.visitLSTORE(this);
  }
}
