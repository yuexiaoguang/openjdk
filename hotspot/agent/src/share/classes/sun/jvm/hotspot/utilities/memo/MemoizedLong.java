package sun.jvm.hotspot.utilities.memo;

/** A memoized long. Override {@link #computeValue} in subclasses;
    call {@link #getValue} in using code. */
public abstract class MemoizedLong {
  private boolean computed;
  private long value;

  /** Should compute the value of this memoized object. This will only
      be called once, upon the first call to {@link #getValue}. */
  protected abstract long computeValue();

  /** Public accessor for the memoized value. */
  public long getValue() {
    if (!computed) {
      value = computeValue();
      computed = true;
    }
    return value;
  }
}
