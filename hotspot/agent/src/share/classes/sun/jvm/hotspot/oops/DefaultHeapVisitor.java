package sun.jvm.hotspot.oops;

/** A DefaultHeapVisitor implements basic no-op HeapVisitor
    functionality. */
public class DefaultHeapVisitor implements HeapVisitor {
  public void prologue(long usedSize) {}
  public boolean doObj(Oop obj)          {return false;}
  public void epilogue()              {}
}
