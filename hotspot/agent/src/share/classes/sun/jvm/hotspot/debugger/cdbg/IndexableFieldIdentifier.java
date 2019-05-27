package sun.jvm.hotspot.debugger.cdbg;

/** Identifier for indices of arrays */
public interface IndexableFieldIdentifier extends FieldIdentifier {
  public int getIndex();
}
