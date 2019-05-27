package java.rmi;

/**
 * A <code>NotBoundException</code> is thrown if an attempt
 * is made to lookup or unbind in the registry a name that has
 * no associated binding.
 */
public class NotBoundException extends java.lang.Exception {

    /* indicate compatibility with JDK 1.1.x version of class */
    private static final long serialVersionUID = -1857741824849069317L;

    /**
     * Constructs a <code>NotBoundException</code> with no
     * specified detail message.
     * @since JDK1.1
     */
    public NotBoundException() {
        super();
    }

    /**
     * Constructs a <code>NotBoundException</code> with the specified
     * detail message.
     *
     * @param s the detail message
     * @since JDK1.1
     */
    public NotBoundException(String s) {
        super(s);
    }
}
