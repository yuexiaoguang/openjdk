/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.generic;

/**
 * MONITORENTER - Enter monitor for object
 * <PRE>Stack: ..., objectref -&gt; ...</PRE>
 */
public class MONITORENTER extends Instruction
  implements ExceptionThrower, StackConsumer {
  public MONITORENTER() {
    super(com.sun.org.apache.bcel.internal.Constants.MONITORENTER, (short)1);
  }

  public Class[] getExceptions() {
    return new Class[] { com.sun.org.apache.bcel.internal.ExceptionConstants.NULL_POINTER_EXCEPTION };
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
    v.visitExceptionThrower(this);
    v.visitStackConsumer(this);
    v.visitMONITORENTER(this);
  }
}
