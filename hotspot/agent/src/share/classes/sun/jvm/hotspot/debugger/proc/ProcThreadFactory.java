package sun.jvm.hotspot.debugger.proc;

import sun.jvm.hotspot.debugger.*;

/** An interface used only internally by the ProcDebugger to be able to
    create platform-specific Thread objects */
public interface ProcThreadFactory {
  public ThreadProxy createThreadWrapper(Address threadIdentifierAddr);
  public ThreadProxy createThreadWrapper(long id);
}
