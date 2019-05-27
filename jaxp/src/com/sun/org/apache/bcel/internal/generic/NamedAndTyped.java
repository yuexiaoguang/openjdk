/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.generic;

/**
 * Denote entity that has both name and type. This is true for local variables,
 * methods and fields.
 */
public interface NamedAndTyped {
  public String getName();
  public Type   getType();
  public void   setName(String name);
  public void   setType(Type type);

}
