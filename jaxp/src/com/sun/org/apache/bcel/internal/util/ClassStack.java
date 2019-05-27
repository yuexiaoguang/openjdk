/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.util;

import java.util.Stack;
import com.sun.org.apache.bcel.internal.classfile.JavaClass;

/**
 * Utility class implementing a (typesafe) stack of JavaClass objects.
*/
public class ClassStack implements java.io.Serializable {
  private Stack stack = new Stack();

  public void      push(JavaClass clazz) { stack.push(clazz); }
  public JavaClass pop()                 { return (JavaClass)stack.pop(); }
  public JavaClass top()                 { return (JavaClass)stack.peek(); }
  public boolean   empty()               { return stack.empty(); }
}
