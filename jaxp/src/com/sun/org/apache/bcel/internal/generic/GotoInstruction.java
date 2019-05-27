/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.generic;

/**
 * Super class for GOTO
 */
public abstract class GotoInstruction extends BranchInstruction
  implements UnconditionalBranch
{
  GotoInstruction(short opcode, InstructionHandle target) {
    super(opcode, target);
  }

  /**
   * Empty constructor needed for the Class.newInstance() statement in
   * Instruction.readInstruction(). Not to be used otherwise.
   */
  GotoInstruction(){}
}
