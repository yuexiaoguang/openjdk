/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.generic;

/**
 * Super class for the IFxxx family of instructions.
 */
public abstract class IfInstruction extends BranchInstruction implements StackConsumer {
  /**
   * Empty constructor needed for the Class.newInstance() statement in
   * Instruction.readInstruction(). Not to be used otherwise.
   */
  IfInstruction() {}

  /**
   * @param instruction Target instruction to branch to
   */
  protected IfInstruction(short opcode, InstructionHandle target) {
    super(opcode, target);
  }

  /**
   * @return negation of instruction, e.g. IFEQ.negate() == IFNE
   */
  public abstract IfInstruction negate();
}
