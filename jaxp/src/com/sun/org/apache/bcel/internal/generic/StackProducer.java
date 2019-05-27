/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.generic;

/**
 * Denote an instruction that may produce a value on top of the stack
 * (this excludes DUP_X1, e.g.)
 */
public interface StackProducer {
  /** @return how many words are produced on stack
   */
  public int produceStack(ConstantPoolGen cpg);
}
