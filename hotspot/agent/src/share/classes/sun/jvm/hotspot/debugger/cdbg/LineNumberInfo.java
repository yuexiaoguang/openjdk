package sun.jvm.hotspot.debugger.cdbg;

import sun.jvm.hotspot.debugger.*;

/** Describes line number information for a given range of program
    counters. */
public interface LineNumberInfo {
  /** Not specified whether this is an absolute or relative path. */
  public String  getSourceFileName();
  public int     getLineNumber();
  public Address getStartPC();
  /** FIXME: specify whether this is inclusive or exclusive (currently
      when BasicLineNumberMapping.recomputeEndPCs() is called, this is
      exclusive) */
  public Address getEndPC();
}
