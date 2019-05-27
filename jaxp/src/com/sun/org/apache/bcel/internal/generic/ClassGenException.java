/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.generic;

/**
 * Thrown on internal errors. Extends RuntimeException so it hasn't to be declared
 * in the throws clause every time.
 */
public class ClassGenException extends RuntimeException {
  public ClassGenException() { super(); }
  public ClassGenException(String s) { super(s); }
}
