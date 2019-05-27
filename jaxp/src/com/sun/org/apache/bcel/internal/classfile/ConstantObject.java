/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.classfile;

/**
 * This interface denotes those constants that have a "natural" value,
 * such as ConstantLong, ConstantString, etc..
 */
public interface ConstantObject {
  /** @return object representing the constant, e.g., Long for ConstantLong
   */
  public abstract Object getConstantValue(ConstantPool cp);
}
