package sun.jvm.hotspot.utilities.memo;

/** A memoized char. Override {@link #computeValue} in subclasses;
    call {@link #getValue} in using code. */
public abstract class MemoizedChar {
  private boolean computed;
  private char value;

  /** Should compute the value of this memoized object. This will only
      be called once, upon the first call to {@link #getValue}. */
  protected abstract char computeValue();

  /** Public accessor for the memoized value. */
  public char getValue() {
    if (!computed) {
      value = computeValue();
      computed = true;
    }
    return value;
  }
}
