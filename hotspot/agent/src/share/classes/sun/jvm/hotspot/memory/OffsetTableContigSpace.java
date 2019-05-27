package sun.jvm.hotspot.memory;

import sun.jvm.hotspot.debugger.*;

/** No additional functionality for now */
public class OffsetTableContigSpace extends ContiguousSpace {
  public OffsetTableContigSpace(Address addr) {
    super(addr);
  }
}
