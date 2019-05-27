/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.Constants;
import java.util.Objects;

/**
 * Returnaddress, the type JSR or JSR_W instructions push upon the stack.
 */
public class ReturnaddressType extends Type {

  public static final ReturnaddressType NO_TARGET = new ReturnaddressType();
  private InstructionHandle returnTarget;

  /**
   * A Returnaddress [that doesn't know where to return to].
   */
  private ReturnaddressType(){
    super(Constants.T_ADDRESS, "<return address>");
  }

  /**
   * Creates a ReturnaddressType object with a target.
   */
  public ReturnaddressType(InstructionHandle returnTarget) {
    super(Constants.T_ADDRESS, "<return address targeting "+returnTarget+">");
        this.returnTarget = returnTarget;
  }

  @Override
  public int hashCode() {
      return Objects.hashCode(this.returnTarget);
  }

  /**
   * Returns if the two Returnaddresses refer to the same target.
   */
  @Override
  public boolean equals(Object rat){
    if(!(rat instanceof ReturnaddressType))
      return false;

    return ((ReturnaddressType)rat).returnTarget.equals(this.returnTarget);
  }

  /**
   * @return the target of this ReturnaddressType
   */
  public InstructionHandle getTarget(){
    return returnTarget;
  }
}
