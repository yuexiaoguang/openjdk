/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.util;

import java.util.HashMap;
import java.util.Collection;
import com.sun.org.apache.bcel.internal.classfile.JavaClass;

/**
 * Utility class implementing a (typesafe) set of JavaClass objects.
 * Since JavaClass has no equals() method, the name of the class is
 * used for comparison.
*/
public class ClassSet implements java.io.Serializable {
  private HashMap _map = new HashMap();

  public boolean add(JavaClass clazz) {
    boolean result = false;

    if(!_map.containsKey(clazz.getClassName())) {
      result = true;
      _map.put(clazz.getClassName(), clazz);
    }

    return result;
  }

  public void      remove(JavaClass clazz) { _map.remove(clazz.getClassName()); }
  public boolean   empty()                 { return _map.isEmpty(); }

  public JavaClass[] toArray() {
    Collection values = _map.values();
    JavaClass[] classes = new JavaClass[values.size()];
    values.toArray(classes);
    return classes;
  }

  public String[] getClassNames() {
    return (String[])_map.keySet().toArray(new String[_map.keySet().size()]);
  }
}
