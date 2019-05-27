/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.generic;

import java.io.*;
import com.sun.org.apache.bcel.internal.util.ByteSequence;

/**
 * IINC - Increment local variable by constant
 */
public class IINC extends LocalVariableInstruction {
  private boolean wide;
  private int     c;

  /**
   * Empty constructor needed for the Class.newInstance() statement in
   * Instruction.readInstruction(). Not to be used otherwise.
   */
  IINC() {}

  /**
   * @param n index of local variable
   * @param c increment factor
   */
  public IINC(int n, int c) {
    super(); // Default behaviour of LocalVariableInstruction causes error

    this.opcode = com.sun.org.apache.bcel.internal.Constants.IINC;
    this.length = (short)3;

    setIndex(n);    // May set wide as side effect
    setIncrement(c);
  }

  /**
   * Dump instruction as byte code to stream out.
   * @param out Output stream
   */
  public void dump(DataOutputStream out) throws IOException {
    if(wide) // Need WIDE prefix ?
      out.writeByte(com.sun.org.apache.bcel.internal.Constants.WIDE);

    out.writeByte(opcode);

    if(wide) {
      out.writeShort(n);
      out.writeShort(c);
    } else {
      out.writeByte(n);
      out.writeByte(c);
    }
  }

  private final void setWide() {
    if(wide = ((n > com.sun.org.apache.bcel.internal.Constants.MAX_SHORT) ||
               (Math.abs(c) > Byte.MAX_VALUE)))
      length = 6; // wide byte included
    else
      length = 3;
  }

  /**
   * Read needed data (e.g. index) from file.
   */
  protected void initFromFile(ByteSequence bytes, boolean wide) throws IOException
  {
    this.wide = wide;

    if(wide) {
      length = 6;
      n = bytes.readUnsignedShort();
      c = bytes.readShort();
    } else {
      length = 3;
      n = bytes.readUnsignedByte();
      c = bytes.readByte();
    }
  }

  /**
   * @return mnemonic for instruction
   */
  public String toString(boolean verbose) {
    return super.toString(verbose) + " " + c;
  }

  /**
   * Set index of local variable.
   */
  public final void setIndex(int n) {
    if(n < 0)
      throw new ClassGenException("Negative index value: " + n);

    this.n = n;
    setWide();
  }

  /**
   * @return increment factor
   */
  public final int getIncrement() { return c; }

  /**
   * Set increment factor.
   */
  public final void setIncrement(int c) {
    this.c = c;
    setWide();
  }

  /** @return int type
   */
  public Type getType(ConstantPoolGen cp) {
    return Type.INT;
  }

  /**
   * Call corresponding visitor method(s). The order is:
   * Call visitor methods of implemented interfaces first, then
   * call methods according to the class hierarchy in descending order,
   * i.e., the most specific visitXXX() call comes last.
   *
   * @param v Visitor object
   */
  public void accept(Visitor v) {
    v.visitLocalVariableInstruction(this);
    v.visitIINC(this);
  }
}
