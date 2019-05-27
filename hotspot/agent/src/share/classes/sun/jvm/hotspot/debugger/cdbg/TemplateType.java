package sun.jvm.hotspot.debugger.cdbg;

import java.util.*;

public interface TemplateType extends Type {
  public int  getNumTemplateArguments();
  public Type instantiate(Type[] arguments);
  public Type instantiate(List/*<Type>*/ arguments);
}
