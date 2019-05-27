/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.generic;

import java.io.*;
import com.sun.org.apache.bcel.internal.util.ByteSequence;

/**
 * RET - Return from subroutine
 *
 * <PRE>Stack: ..., -&gt; ..., address</PRE>
 *
 * @author  <A HREF="mailto:markus.dahm@berlin.de">M. Dahm</A>
 */
public class RET extends Instruction implements IndexedInstruction, TypedInstruction {
  private boolean wide;
  private int     index; // index to local variable containg the return address

  /**
   * Empty constructor needed for the Class.newInstance() statement in
   * Instruction.readInstruction(). Not to be used otherwise.
   */
  RET() {}

  public RET(int index) {
    super(com.sun.org.apache.bcel.internal.Constants.RET, (short)2);
    setIndex(index);   // May set wide as side effect
  }

  /**
   * Dump instruction as byte code to stream out.
   * @param out Output stream
   */
  public void dump(DataOutputStream out) throws IOException {
    if(wide)
      out.writeByte(com.sun.org.apache.bcel.internal.Constants.WIDE);

    out.writeByte(opcode);

    if(wide)
      out.writeShort(index);
    else
      out.writeByte(index);
  }

  private final void setWide() {
    if(wide = index > com.sun.org.apache.bcel.internal.Constants.MAX_BYTE)
      length = 4; // Including the wide byte
    else
      length = 2;
  }

  /**
   * Read needed data (e.g. index) from file.
   */
  protected void initFromFile(ByteSequence bytes, boolean wide) throws IOException
  {
    this.wide = wide;

    if(wide) {
      index  = bytes.readUnsignedShort();
      length = 4;
    } else {
      index = bytes.readUnsignedByte();
      length = 2;
    }
  }

  /**
   * @return index of local variable containg the return address
   */
  public final int getIndex() { return index; }

  /**
   * Set index of local variable containg the return address
   */
  public final void setIndex(int n) {
    if(n < 0)
      throw new ClassGenException("Negative index value: " + n);

    index = n;
    setWide();
  }

  /**
   * @return mnemonic for instruction
   */
  public String toString(boolean verbose) {
    return super.toString(verbose) + " " + index;
  }

  /** @return return address type
   */
  public Type getType(ConstantPoolGen cp) {
      return ReturnaddressType.NO_TARGET;
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
    v.visitRET(this);
  }
}
