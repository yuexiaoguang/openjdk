package sun.jvm.hotspot.oops;

public class UnknownOopException extends RuntimeException {
  public UnknownOopException() {
    super();
  }

  public UnknownOopException(String detail) {
    super(detail);
  }
}
