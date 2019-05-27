/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.Constants;
import com.sun.org.apache.bcel.internal.ExceptionConstants;

/**
 * Super class for the xRETURN family of instructions.
 */
public abstract class ReturnInstruction extends Instruction
  implements ExceptionThrower, TypedInstruction, StackConsumer {
  /**
   * Empty constructor needed for the Class.newInstance() statement in
   * Instruction.readInstruction(). Not to be used otherwise.
   */
  ReturnInstruction() {}

  /**
   * @param opcode of instruction
   */
  protected ReturnInstruction(short opcode) {
    super(opcode, (short)1);
  }

  public Type getType() {
    switch(opcode) {
      case Constants.IRETURN: return Type.INT;
      case Constants.LRETURN: return Type.LONG;
      case Constants.FRETURN: return Type.FLOAT;
      case Constants.DRETURN: return Type.DOUBLE;
      case Constants.ARETURN: return Type.OBJECT;
      case Constants.RETURN:  return Type.VOID;

    default: // Never reached
      throw new ClassGenException("Unknown type " + opcode);
    }
  }

  public Class[] getExceptions() {
    return new Class[] { ExceptionConstants.ILLEGAL_MONITOR_STATE };
  }

  /** @return type associated with the instruction
   */
  public Type getType(ConstantPoolGen cp) {
    return getType();
  }
}
