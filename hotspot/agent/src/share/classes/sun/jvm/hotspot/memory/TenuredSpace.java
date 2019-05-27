package sun.jvm.hotspot.memory;

import sun.jvm.hotspot.debugger.*;

/** No additional functionality for now */
public class TenuredSpace extends OffsetTableContigSpace {
  public TenuredSpace(Address addr) {
    super(addr);
  }
}
