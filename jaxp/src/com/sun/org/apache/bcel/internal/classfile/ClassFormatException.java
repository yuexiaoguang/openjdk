/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.classfile;

/**
 * Thrown when the BCEL attempts to read a class file and determines
 * that the file is malformed or otherwise cannot be interpreted as a
 * class file.
 */
public class ClassFormatException extends RuntimeException {
  public ClassFormatException() { super(); }
  public ClassFormatException(String s) { super(s); }
}
