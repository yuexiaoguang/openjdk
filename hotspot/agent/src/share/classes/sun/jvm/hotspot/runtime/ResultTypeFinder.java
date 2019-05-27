package sun.jvm.hotspot.runtime;

import sun.jvm.hotspot.oops.*;

/** Specialized SignatureIterator: Used to compute the result type. */
public class ResultTypeFinder extends SignatureInfo {
  protected void set(int size, int type)    { if (isReturnType()) this.type = type; }
  public ResultTypeFinder(Symbol signature) { super(signature); }
}
