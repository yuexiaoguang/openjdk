/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.generic;

/**
 * LDC2_W - Push long or double from constant pool
 *
 * <PRE>Stack: ... -&gt; ..., item.word1, item.word2</PRE>
 */
public class LDC2_W extends CPInstruction
  implements PushInstruction, TypedInstruction {
  /**
   * Empty constructor needed for the Class.newInstance() statement in
   * Instruction.readInstruction(). Not to be used otherwise.
   */
  LDC2_W() {}

  public LDC2_W(int index) {
    super(com.sun.org.apache.bcel.internal.Constants.LDC2_W, index);
  }

  public Type getType(ConstantPoolGen cpg) {
    switch(cpg.getConstantPool().getConstant(index).getTag()) {
    case com.sun.org.apache.bcel.internal.Constants.CONSTANT_Long:   return Type.LONG;
    case com.sun.org.apache.bcel.internal.Constants.CONSTANT_Double: return Type.DOUBLE;
    default: // Never reached
      throw new RuntimeException("Unknown constant type " + opcode);
    }
  }

  public Number getValue(ConstantPoolGen cpg) {
    com.sun.org.apache.bcel.internal.classfile.Constant c = cpg.getConstantPool().getConstant(index);

    switch(c.getTag()) {
    case com.sun.org.apache.bcel.internal.Constants.CONSTANT_Long:
        return new Long(((com.sun.org.apache.bcel.internal.classfile.ConstantLong)c).getBytes());

    case com.sun.org.apache.bcel.internal.Constants.CONSTANT_Double:
        return new Double(((com.sun.org.apache.bcel.internal.classfile.ConstantDouble)c).getBytes());

    default: // Never reached
      throw new RuntimeException("Unknown or invalid constant type at " + index);
      }
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
    v.visitTypedInstruction(this);
    v.visitCPInstruction(this);
    v.visitLDC2_W(this);
  }
}
