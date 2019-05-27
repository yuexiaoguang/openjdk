package sun.reflect.annotation;

import java.lang.annotation.*;

/**
 * ExceptionProxy for TypeNotPresentException.
 */
public class TypeNotPresentExceptionProxy extends ExceptionProxy {
    private static final long serialVersionUID = 5565925172427947573L;
    String typeName;
    Throwable cause;

    public TypeNotPresentExceptionProxy(String typeName, Throwable cause) {
        this.typeName = typeName;
        this.cause = cause;
    }

    protected RuntimeException generateException() {
        return new TypeNotPresentException(typeName, cause);
    }
}
