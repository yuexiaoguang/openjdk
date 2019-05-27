/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.util;

import com.sun.org.apache.bcel.internal.classfile.JavaClass;

/**
 * Abstract definition of a class repository. Instances may be used
 * to load classes from different sources and may be used in the
 * Repository.setRepository method.
 */
public interface Repository extends java.io.Serializable {
  /**
   * Store the provided class under "clazz.getClassName()"
   */
  public void storeClass(JavaClass clazz);

  /**
   * Remove class from repository
   */
  public void removeClass(JavaClass clazz);

  /**
   * Find the class with the name provided, if the class
   * isn't there, return NULL.
   */
  public JavaClass findClass(String className);

  /**
   * Find the class with the name provided, if the class
   * isn't there, make an attempt to load it.
   */
  public JavaClass loadClass(String className)
    throws java.lang.ClassNotFoundException;

  /**
   * Find the JavaClass instance for the given run-time class object
   */
  public JavaClass loadClass(Class clazz)
    throws java.lang.ClassNotFoundException;

  /** Clear all entries from cache.
   */
  public void clear();
}
