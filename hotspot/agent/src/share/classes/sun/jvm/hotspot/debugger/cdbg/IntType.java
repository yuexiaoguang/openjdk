package sun.jvm.hotspot.debugger.cdbg;

public interface IntType extends Type {
  /** Returns size in bytes of this type */
  public int getIntSize();

  /** Indicates whether this type is unsigned */
  public boolean isUnsigned();
}
