package sun.jvm.hotspot.runtime;

import sun.jvm.hotspot.debugger.*;

/** A generic interface for visiting addresses. Used by the frame/oop
    map iteration mechanisms. */
public interface AddressVisitor {
  public void visitAddress(Address addr);
  public void visitCompOopAddress(Address addr);
}
