package java.lang.reflect;

/**
 * Thrown when a semantically malformed parameterized type is
 * encountered by a reflective method that needs to instantiate it.
 * For example, if the number of type arguments to a parameterized type
 * is wrong.
 */
public class MalformedParameterizedTypeException extends RuntimeException {
    private static final long serialVersionUID = -5696557788586220964L;
}
