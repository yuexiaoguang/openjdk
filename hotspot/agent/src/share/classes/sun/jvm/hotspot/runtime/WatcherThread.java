package sun.jvm.hotspot.runtime;

import sun.jvm.hotspot.debugger.*;
import sun.jvm.hotspot.types.*;

/** These will never show up in the threads list (from Threads.first()) */
public class WatcherThread extends Thread {
  public WatcherThread(Address addr) {
    super(addr);
  }

  public boolean isWatcherThread() { return true; }
}
