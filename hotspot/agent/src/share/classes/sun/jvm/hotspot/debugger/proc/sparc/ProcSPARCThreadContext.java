package sun.jvm.hotspot.debugger.proc.sparc;

import sun.jvm.hotspot.debugger.*;
import sun.jvm.hotspot.debugger.sparc.*;
import sun.jvm.hotspot.debugger.proc.*;

public class ProcSPARCThreadContext extends SPARCThreadContext {
  private ProcDebugger debugger;

  public ProcSPARCThreadContext(ProcDebugger debugger) {
    super();
    this.debugger = debugger;
  }

  public void setRegisterAsAddress(int index, Address value) {
    setRegister(index, debugger.getAddressValue(value));
  }

  public Address getRegisterAsAddress(int index) {
    return debugger.newAddress(getRegister(index));
  }
}
