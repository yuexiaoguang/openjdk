/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.generic;

/**
 * Super class for stack operations like DUP and POP.
 */
public abstract class StackInstruction extends Instruction {
  /**
   * Empty constructor needed for the Class.newInstance() statement in
   * Instruction.readInstruction(). Not to be used otherwise.
   */
  StackInstruction() {}

  /**
   * @param opcode instruction opcode
   */
  protected StackInstruction(short opcode) {
    super(opcode, (short)1);
  }

  /** @return Type.UNKNOWN
   */
  public Type getType(ConstantPoolGen cp) {
    return Type.UNKNOWN;
  }
}
