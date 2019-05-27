package sun.jvm.hotspot.types;

public class WrongTypeException extends RuntimeException {
  public WrongTypeException() {
    super();
  }

  public WrongTypeException(String detail) {
    super(detail);
  }
}
