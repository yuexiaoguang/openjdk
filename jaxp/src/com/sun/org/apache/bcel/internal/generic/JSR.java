/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.generic;

import java.io.*;

/**
 * JSR - Jump to subroutine
 */
public class JSR extends JsrInstruction implements VariableLengthInstruction {
  /**
   * Empty constructor needed for the Class.newInstance() statement in
   * Instruction.readInstruction(). Not to be used otherwise.
   */
  JSR() {}

  public JSR(InstructionHandle target) {
    super(com.sun.org.apache.bcel.internal.Constants.JSR, target);
  }

  /**
   * Dump instruction as byte code to stream out.
   * @param out Output stream
   */
  public void dump(DataOutputStream out) throws IOException {
    index = getTargetOffset();
    if(opcode == com.sun.org.apache.bcel.internal.Constants.JSR)
      super.dump(out);
    else { // JSR_W
      index = getTargetOffset();
      out.writeByte(opcode);
      out.writeInt(index);
    }
  }

  protected int updatePosition(int offset, int max_offset) {
    int i = getTargetOffset(); // Depending on old position value

    position += offset; // Position may be shifted by preceding expansions

    if(Math.abs(i) >= (32767 - max_offset)) { // to large for short (estimate)
      opcode  = com.sun.org.apache.bcel.internal.Constants.JSR_W;
      length = 5;
      return 2; // 5 - 3
    }

    return 0;
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
    v.visitVariableLengthInstruction(this);
    v.visitBranchInstruction(this);
    v.visitJsrInstruction(this);
    v.visitJSR(this);
  }
}
