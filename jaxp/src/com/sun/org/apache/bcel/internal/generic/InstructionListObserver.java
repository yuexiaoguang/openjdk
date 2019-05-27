/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.generic;

/**
 * Implement this interface if you're interested in changes to an InstructionList object
 * and register yourself with addObserver().
 */
public interface InstructionListObserver {
  public void notify(InstructionList list);
}
