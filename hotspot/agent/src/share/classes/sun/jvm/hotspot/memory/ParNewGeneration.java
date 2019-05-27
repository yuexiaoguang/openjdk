package sun.jvm.hotspot.memory;

import sun.jvm.hotspot.debugger.*;

public class ParNewGeneration extends DefNewGeneration {
  public ParNewGeneration(Address addr) {
    super(addr);
  }

  public Generation.Name kind() {
    return Generation.Name.PAR_NEW;
  }
}
