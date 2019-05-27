package sun.jvm.hotspot.utilities;

public interface BitMapClosure {
  /** Called when specified bit in map is set */
  public void doBit(int offset);
}
