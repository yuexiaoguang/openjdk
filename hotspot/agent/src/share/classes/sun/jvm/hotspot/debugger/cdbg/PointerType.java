package sun.jvm.hotspot.debugger.cdbg;

public interface PointerType extends Type {
  public Type getTargetType();
}
