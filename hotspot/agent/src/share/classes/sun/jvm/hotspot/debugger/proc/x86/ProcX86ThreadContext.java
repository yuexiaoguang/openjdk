package sun.jvm.hotspot.debugger.proc.x86;

import sun.jvm.hotspot.debugger.*;
import sun.jvm.hotspot.debugger.x86.*;
import sun.jvm.hotspot.debugger.proc.*;

public class ProcX86ThreadContext extends X86ThreadContext {
  private ProcDebugger debugger;

  public ProcX86ThreadContext(ProcDebugger debugger) {
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
