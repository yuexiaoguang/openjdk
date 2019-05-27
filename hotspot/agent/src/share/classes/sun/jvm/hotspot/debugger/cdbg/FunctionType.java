package sun.jvm.hotspot.debugger.cdbg;

public interface FunctionType extends Type {
  public Type getReturnType();
  public int  getNumArguments();
  public Type getArgumentType(int i);
}
