package sun.jvm.hotspot.utilities.memo;

/** A memoized float. Override {@link #computeValue} in subclasses;
    call {@link #getValue} in using code. */
public abstract class MemoizedFloat {
  private boolean computed;
  private float value;

  /** Should compute the value of this memoized object. This will only
      be called once, upon the first call to {@link #getValue}. */
  protected abstract float computeValue();

  /** Public accessor for the memoized value. */
  public float getValue() {
    if (!computed) {
      value = computeValue();
      computed = true;
    }
    return value;
  }
}
