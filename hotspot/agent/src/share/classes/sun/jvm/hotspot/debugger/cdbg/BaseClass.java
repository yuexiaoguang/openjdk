package sun.jvm.hotspot.debugger.cdbg;

/** Models a base class; effectively just provides the "virtual"
    keyword as well as public/private derivation information. */
public interface BaseClass {
  /** See {@link sun.jvm.hotspot.debugger.cdbg.AccessControl} */
  public int     getAccessControl();
  public boolean isVirtual();
  public Type    getType();
}
