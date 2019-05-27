package sun.jvm.hotspot.debugger.cdbg;

public interface ArrayType extends Type {
  public Type getElementType();
  public int  getLength();
}
