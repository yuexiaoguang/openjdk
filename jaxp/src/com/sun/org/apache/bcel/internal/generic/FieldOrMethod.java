/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.classfile.*;

/**
 * Super class for InvokeInstruction and FieldInstruction, since they have
 * some methods in common!
 */
public abstract class FieldOrMethod extends CPInstruction implements LoadClass {
  /**
   * Empty constructor needed for the Class.newInstance() statement in
   * Instruction.readInstruction(). Not to be used otherwise.
   */
  FieldOrMethod() {}

  /**
   * @param index to constant pool
   */
  protected FieldOrMethod(short opcode, int index) {
    super(opcode, index);
  }

  /** @return signature of referenced method/field.
   */
  public String getSignature(ConstantPoolGen cpg) {
    ConstantPool        cp   = cpg.getConstantPool();
    ConstantCP          cmr  = (ConstantCP)cp.getConstant(index);
    ConstantNameAndType cnat = (ConstantNameAndType)cp.getConstant(cmr.getNameAndTypeIndex());

    return ((ConstantUtf8)cp.getConstant(cnat.getSignatureIndex())).getBytes();
  }

  /** @return name of referenced method/field.
   */
  public String getName(ConstantPoolGen cpg) {
    ConstantPool        cp   = cpg.getConstantPool();
    ConstantCP          cmr  = (ConstantCP)cp.getConstant(index);
    ConstantNameAndType cnat = (ConstantNameAndType)cp.getConstant(cmr.getNameAndTypeIndex());
    return ((ConstantUtf8)cp.getConstant(cnat.getNameIndex())).getBytes();
  }

  /** @return name of the referenced class/interface
   */
  public String getClassName(ConstantPoolGen cpg) {
    ConstantPool cp  = cpg.getConstantPool();
    ConstantCP   cmr = (ConstantCP)cp.getConstant(index);
    return cp.getConstantString(cmr.getClassIndex(), com.sun.org.apache.bcel.internal.Constants.CONSTANT_Class).replace('/', '.');
  }

  /** @return type of the referenced class/interface
   */
  public ObjectType getClassType(ConstantPoolGen cpg) {
    return new ObjectType(getClassName(cpg));
  }

  /** @return type of the referenced class/interface
   */
  public ObjectType getLoadClassType(ConstantPoolGen cpg) {
    return getClassType(cpg);
  }
}
