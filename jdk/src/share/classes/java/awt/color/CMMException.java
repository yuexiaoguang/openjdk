package java.awt.color;


/**
 * This exception is thrown if the native CMM returns an error.
 */
public class CMMException extends java.lang.RuntimeException {

    /**
     *  Constructs a CMMException with the specified detail message.
     *  @param s the specified detail message
     */
    public CMMException (String s) {
        super (s);
    }
}
