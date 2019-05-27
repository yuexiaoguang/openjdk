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
 * and represents a reference to a long object.
 */
public final class ConstantLong extends Constant implements ConstantObject {
  private long bytes;

  /**
   * @param bytes Data
   */
  public ConstantLong(long bytes)
  {
    super(Constants.CONSTANT_Long);
    this.bytes = bytes;
  }
  /**
   * Initialize from another object.
   */
  public ConstantLong(ConstantLong c) {
    this(c.getBytes());
  }
  /**
   * Initialize instance from file data.
   *
   * @param file Input stream
   * @throws IOException
   */
  ConstantLong(DataInputStream file) throws IOException
  {
    this(file.readLong());
  }
  /**
   * Called by objects that are traversing the nodes of the tree implicitely
   * defined by the contents of a Java class. I.e., the hierarchy of methods,
   * fields, attributes, etc. spawns a tree of objects.
   *
   * @param v Visitor object
   */
  public void accept(Visitor v) {
    v.visitConstantLong(this);
  }
  /**
   * Dump constant long to file stream in binary format.
   *
   * @param file Output file stream
   * @throws IOException
   */
  public final void dump(DataOutputStream file) throws IOException
  {
    file.writeByte(tag);
    file.writeLong(bytes);
  }
  /**
   * @return data, i.e., 8 bytes.
   */
  public final long getBytes() { return bytes; }
  /**
   * @param bytes.
   */
  public final void setBytes(long bytes) {
    this.bytes = bytes;
  }
  /**
   * @return String representation.
   */
  public final String toString() {
    return super.toString() + "(bytes = " + bytes + ")";
  }

  /** @return Long object
   */
  public Object getConstantValue(ConstantPool cp) {
    return new Long(bytes);
  }
}
