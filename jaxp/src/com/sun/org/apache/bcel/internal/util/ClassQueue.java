/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.util;

import java.util.LinkedList;
import com.sun.org.apache.bcel.internal.classfile.JavaClass;

/**
 * Utility class implementing a (typesafe) queue of JavaClass
 * objects.
*/
public class ClassQueue implements java.io.Serializable {
  protected LinkedList vec  = new LinkedList();

  public void enqueue(JavaClass clazz) { vec.addLast(clazz); }

  public JavaClass dequeue()                {
    return (JavaClass)vec.removeFirst();
  }

  public boolean empty() { return vec.isEmpty(); }

  public String toString() {
    return vec.toString();
  }
}
