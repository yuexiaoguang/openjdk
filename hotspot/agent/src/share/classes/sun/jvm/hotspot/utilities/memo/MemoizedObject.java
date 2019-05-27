package sun.jvm.hotspot.utilities.memo;

/** A memoized object. Override {@link #computeValue} in subclasses;
    call {@link #getValue} in using code. */
public abstract class MemoizedObject {
  private boolean computed;
  private Object value;

  /** Should compute the value of this memoized object. This will only
      be called once, upon the first call to {@link #getValue}. */
  protected abstract Object computeValue();

  /** Public accessor for the memoized value. */
  public Object getValue() {
    if (!computed) {
      value = computeValue();
      computed = true;
    }
    return value;
  }
}
