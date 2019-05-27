package sun.jvm.hotspot.utilities;

import sun.jvm.hotspot.oops.*;

/** Describes an element in a {@link
    sun.jvm.hotspot.utilities.LivenessPath}. It indicates that the
    field in the given object leads to the next element in the
    path. Root elements do not have an object; the terminal element in
    the path does not have a field identifier. */
public class LivenessPathElement {
  LivenessPathElement(Oop obj, FieldIdentifier id) {
    this.obj = obj;
    this.id  = id;
  }

  public boolean isRoot() {
    return (obj == null);
  }

  public boolean isTerminal() {
    return (id == null);
  }

  public Oop getObj() {
    return obj;
  }

  public FieldIdentifier getField() {
    return id;
  }

  //---------------------------------------------------------------------------
  // Internals only below this point
  //
  private Oop             obj;
  private FieldIdentifier id;
}
