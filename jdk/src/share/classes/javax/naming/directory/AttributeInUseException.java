package javax.naming.directory;

import javax.naming.NamingException;

/**
  * This exception is thrown when an operation attempts
  * to add an attribute that already exists.
  * <p>
  * Synchronization and serialization issues that apply to NamingException
  * apply directly here.
  */
public class AttributeInUseException extends NamingException {
    /**
     * Constructs a new instance of AttributeInUseException with
     * an explanation. All other fields are set to null.
     *
     * @param   explanation     Possibly null additional detail about this exception.
     * @see java.lang.Throwable#getMessage
     */
    public AttributeInUseException(String explanation) {
        super(explanation);
    }

    /**
      * Constructs a new instance of AttributeInUseException.
      * All fields are initialized to null.
      */
    public AttributeInUseException() {
        super();
    }

    /**
     * Use serialVersionUID from JNDI 1.1.1 for interoperability
     */
    private static final long serialVersionUID = 4437710305529322564L;
}
