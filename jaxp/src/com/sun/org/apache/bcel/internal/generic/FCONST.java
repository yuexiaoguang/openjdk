/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.generic;

/**
 * FCONST - Push 0.0, 1.0 or 2.0, other values cause an exception
 *
 * <PRE>Stack: ... -&gt; ..., </PRE>
 */
public class FCONST extends Instruction
  implements ConstantPushInstruction, TypedInstruction {
  private float value;

  /**
   * Empty constructor needed for the Class.newInstance() statement in
   * Instruction.readInstruction(). Not to be used otherwise.
   */
  FCONST() {}

  public FCONST(float f) {
    super(com.sun.org.apache.bcel.internal.Constants.FCONST_0, (short)1);

    if(f == 0.0)
      opcode = com.sun.org.apache.bcel.internal.Constants.FCONST_0;
    else if(f == 1.0)
      opcode = com.sun.org.apache.bcel.internal.Constants.FCONST_1;
    else if(f == 2.0)
      opcode = com.sun.org.apache.bcel.internal.Constants.FCONST_2;
    else
      throw new ClassGenException("FCONST can be used only for 0.0, 1.0 and 2.0: " + f);

    value = f;
  }

  public Number getValue() { return new Float(value); }

  /** @return Type.FLOAT
   */
  public Type getType(ConstantPoolGen cp) {
    return Type.FLOAT;
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
    v.visitPushInstruction(this);
    v.visitStackProducer(this);
    v.visitTypedInstruction(this);
    v.visitConstantPushInstruction(this);
    v.visitFCONST(this);
  }
}
