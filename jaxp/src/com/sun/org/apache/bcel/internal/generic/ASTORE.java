/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.generic;

/**
 * ASTORE - Store reference into local variable
 * <PRE>Stack ..., objectref -&gt; ... </PRE>
 */
public class ASTORE extends StoreInstruction {
  /**
   * Empty constructor needed for the Class.newInstance() statement in
   * Instruction.readInstruction(). Not to be used otherwise.
   */
  ASTORE() {
    super(com.sun.org.apache.bcel.internal.Constants.ASTORE, com.sun.org.apache.bcel.internal.Constants.ASTORE_0);
  }

  /** Store reference into local variable
   * @param n index of local variable
   */
  public ASTORE(int n) {
    super(com.sun.org.apache.bcel.internal.Constants.ASTORE, com.sun.org.apache.bcel.internal.Constants.ASTORE_0, n);
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
    v.visitASTORE(this);
  }
}
