/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.classfile;

import  com.sun.org.apache.bcel.internal.Constants;
import  java.io.*;

/**
 * This class is derived from the abstract
 * <A HREF="com.sun.org.apache.bcel.internal.classfile.Constant.html">Constant</A> class
 * and represents a reference to a Double object.
 */
public final class ConstantDouble extends Constant implements ConstantObject {
  private double bytes;

  /**
   * @param bytes Data
   */
  public ConstantDouble(double bytes) {
    super(Constants.CONSTANT_Double);
    this.bytes = bytes;
  }

  /**
   * Initialize from another object.
   */
  public ConstantDouble(ConstantDouble c) {
    this(c.getBytes());
  }

  /**
   * Initialize instance from file data.
   *
   * @param file Input stream
   * @throws IOException
   */
  ConstantDouble(DataInputStream file) throws IOException
  {
    this(file.readDouble());
  }

  /**
   * Called by objects that are traversing the nodes of the tree implicitely
   * defined by the contents of a Java class. I.e., the hierarchy of methods,
   * fields, attributes, etc. spawns a tree of objects.
   *
   * @param v Visitor object
   */
  public void accept(Visitor v) {
    v.visitConstantDouble(this);
  }
  /**
   * Dump constant double to file stream in binary format.
   *
   * @param file Output file stream
   * @throws IOException
   */
  public final void dump(DataOutputStream file) throws IOException
  {
    file.writeByte(tag);
    file.writeDouble(bytes);
  }
  /**
   * @return data, i.e., 8 bytes.
   */
  public final double getBytes() { return bytes; }
  /**
   * @param bytes.
   */
  public final void setBytes(double bytes) {
    this.bytes = bytes;
  }
  /**
   * @return String representation.
   */
  public final String toString()
  {
    return super.toString() + "(bytes = " + bytes + ")";
  }

  /** @return Double object
   */
  public Object getConstantValue(ConstantPool cp) {
    return new Double(bytes);
  }
}
