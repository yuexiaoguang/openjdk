/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.classfile;

import com.sun.org.apache.bcel.internal.Constants;
import java.io.*;

/**
 * This class represents a constant pool reference to an interface method.
 */
public final class ConstantInterfaceMethodref extends ConstantCP {
  /**
   * Initialize from another object.
   */
  public ConstantInterfaceMethodref(ConstantInterfaceMethodref c) {
    super(Constants.CONSTANT_InterfaceMethodref, c.getClassIndex(), c.getNameAndTypeIndex());
  }

  /**
   * Initialize instance from file data.
   *
   * @param file input stream
   * @throws IOException
   */
  ConstantInterfaceMethodref(DataInputStream file) throws IOException
  {
    super(Constants.CONSTANT_InterfaceMethodref, file);
  }

  /**
   * @param class_index Reference to the class containing the method
   * @param name_and_type_index and the method signature
   */
  public ConstantInterfaceMethodref(int class_index,
                                    int name_and_type_index) {
    super(Constants.CONSTANT_InterfaceMethodref, class_index, name_and_type_index);
  }

  /**
   * Called by objects that are traversing the nodes of the tree implicitely
   * defined by the contents of a Java class. I.e., the hierarchy of methods,
   * fields, attributes, etc. spawns a tree of objects.
   *
   * @param v Visitor object
   */
  public void accept(Visitor v) {
    v.visitConstantInterfaceMethodref(this);
  }
}
