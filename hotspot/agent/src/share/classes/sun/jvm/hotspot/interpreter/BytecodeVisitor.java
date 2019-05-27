package sun.jvm.hotspot.interpreter;

import sun.jvm.hotspot.oops.Method;

public interface BytecodeVisitor {
   public void prologue(Method method);
   public void visit(Bytecode bytecode);
   public void epilogue();
}
