package javax.naming;

/**
  * This exception is thrown by methods to indicate that
  * a binding cannot be added because the name is already bound to
  * another object.
  * <p>
  * Synchronization and serialization issues that apply to NamingException
  * apply directly here.
  */
public class NameAlreadyBoundException extends NamingException {
    /**
     * Constructs a new instance of NameAlreadyBoundException using the
     * explanation supplied. All other fields default to null.
     *
     *
     * @param   explanation     Possibly null additional detail about this exception.
     * @see java.lang.Throwable#getMessage
     */
    public NameAlreadyBoundException(String explanation) {
        super(explanation);
    }

    /**
      * Constructs a new instance of NameAlreadyBoundException.
      * All fields are set to null;
      */
    public NameAlreadyBoundException() {
        super();
    }

    /**
     * Use serialVersionUID from JNDI 1.1.1 for interoperability
     */
    private static final long serialVersionUID = -8491441000356780586L;
}
