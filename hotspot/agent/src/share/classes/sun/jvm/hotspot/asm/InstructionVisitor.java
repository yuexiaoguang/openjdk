package sun.jvm.hotspot.asm;

public interface InstructionVisitor {
   public void prologue();
   public void beginInstruction(long currentPc);
   public void printAddress(long address);
   public void print(String format);
   public void endInstruction(long endPc);
   public void epilogue();
}
