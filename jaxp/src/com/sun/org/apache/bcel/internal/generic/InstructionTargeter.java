/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.generic;

/**
 * Denote that a class targets InstructionHandles within an InstructionList. Namely
 * the following implementers:
 */
public interface InstructionTargeter {
  public boolean containsTarget(InstructionHandle ih);
  public void updateTarget(InstructionHandle old_ih, InstructionHandle new_ih);
}
