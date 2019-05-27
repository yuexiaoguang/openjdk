package sun.jvm.hotspot.debugger.proc.amd64;

import sun.jvm.hotspot.debugger.*;
import sun.jvm.hotspot.debugger.amd64.*;
import sun.jvm.hotspot.debugger.proc.*;

public class ProcAMD64ThreadContext extends AMD64ThreadContext {
    private ProcDebugger debugger;

    public ProcAMD64ThreadContext(ProcDebugger debugger) {
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
