package sun.jvm.hotspot.memory;

import sun.jvm.hotspot.debugger.*;

/** Should only be used once */
class GenerationIsInClosure implements SpaceClosure {
  private Address p;
  private Space sp;

  GenerationIsInClosure(Address p) {
    this.p = p;
  }

  public void doSpace(Space s) {
    if (s.contains(p)) {
      sp = s;
    }
  }

  Space space() {
    return sp;
  }
}
