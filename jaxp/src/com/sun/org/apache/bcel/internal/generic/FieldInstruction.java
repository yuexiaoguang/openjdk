/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.classfile.ConstantPool;
import com.sun.org.apache.bcel.internal.classfile.ConstantUtf8;
import com.sun.org.apache.bcel.internal.classfile.ConstantNameAndType;
import com.sun.org.apache.bcel.internal.classfile.ConstantCP;
import com.sun.org.apache.bcel.internal.classfile.*;

/**
 * Super class for the GET/PUTxxx family of instructions.
 */
public abstract class FieldInstruction extends FieldOrMethod
  implements TypedInstruction {
  /**
   * Empty constructor needed for the Class.newInstance() statement in
   * Instruction.readInstruction(). Not to be used otherwise.
   */
  FieldInstruction() {}

  /**
   * @param index to constant pool
   */
  protected FieldInstruction(short opcode, int index) {
    super(opcode, index);
  }

  /**
   * @return mnemonic for instruction with symbolic references resolved
   */
  public String toString(ConstantPool cp) {
    return com.sun.org.apache.bcel.internal.Constants.OPCODE_NAMES[opcode] + " " +
      cp.constantToString(index, com.sun.org.apache.bcel.internal.Constants.CONSTANT_Fieldref);
  }

  /** @return size of field (1 or 2)
   */
  protected int getFieldSize(ConstantPoolGen cpg) {
    return getType(cpg).getSize();
  }

  /** @return return type of referenced field
   */
  public Type getType(ConstantPoolGen cpg) {
    return getFieldType(cpg);
  }

  /** @return type of field
   */
  public Type getFieldType(ConstantPoolGen cpg) {
    return Type.getType(getSignature(cpg));
  }

  /** @return name of referenced field.
   */
  public String getFieldName(ConstantPoolGen cpg) {
    return getName(cpg);
  }
}
