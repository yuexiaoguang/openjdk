/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.generic;

/**
 * Super class for instructions dealing with array access such as IALOAD.
 */
public abstract class ArrayInstruction extends Instruction
  implements ExceptionThrower, TypedInstruction {
  /**
   * Empty constructor needed for the Class.newInstance() statement in
   * Instruction.readInstruction(). Not to be used otherwise.
   */
  ArrayInstruction() {}

  /**
   * @param opcode of instruction
   */
  protected ArrayInstruction(short opcode) {
    super(opcode, (short)1);
  }

  public Class[] getExceptions() {
    return com.sun.org.apache.bcel.internal.ExceptionConstants.EXCS_ARRAY_EXCEPTION;
  }

  /** @return type associated with the instruction
   */
  public Type getType(ConstantPoolGen cp) {
    switch(opcode) {
    case com.sun.org.apache.bcel.internal.Constants.IALOAD: case com.sun.org.apache.bcel.internal.Constants.IASTORE:
      return Type.INT;
    case com.sun.org.apache.bcel.internal.Constants.CALOAD: case com.sun.org.apache.bcel.internal.Constants.CASTORE:
      return Type.CHAR;
    case com.sun.org.apache.bcel.internal.Constants.BALOAD: case com.sun.org.apache.bcel.internal.Constants.BASTORE:
      return Type.BYTE;
    case com.sun.org.apache.bcel.internal.Constants.SALOAD: case com.sun.org.apache.bcel.internal.Constants.SASTORE:
      return Type.SHORT;
    case com.sun.org.apache.bcel.internal.Constants.LALOAD: case com.sun.org.apache.bcel.internal.Constants.LASTORE:
      return Type.LONG;
    case com.sun.org.apache.bcel.internal.Constants.DALOAD: case com.sun.org.apache.bcel.internal.Constants.DASTORE:
      return Type.DOUBLE;
    case com.sun.org.apache.bcel.internal.Constants.FALOAD: case com.sun.org.apache.bcel.internal.Constants.FASTORE:
      return Type.FLOAT;
    case com.sun.org.apache.bcel.internal.Constants.AALOAD: case com.sun.org.apache.bcel.internal.Constants.AASTORE:
      return Type.OBJECT;

    default: throw new ClassGenException("Oops: unknown case in switch" + opcode);
    }
  }
}
