package javax.naming;

import javax.naming.Name;

/**
  * This exception is thrown when a method
  * produces a result that exceeds a size-related limit.
  * This can happen, for example, if the result contains
  * more objects than the user requested, or when the size
  * of the result exceeds some implementation-specific limit.
  * <p>
  * Synchronization and serialization issues that apply to NamingException
  * apply directly here.
  */
public class SizeLimitExceededException extends LimitExceededException {
    /**
     * Constructs a new instance of SizeLimitExceededException.
     * All fields default to null.
     */
    public SizeLimitExceededException() {
        super();
    }

    /**
     * Constructs a new instance of SizeLimitExceededException using an
     * explanation. All other fields default to null.
     *
     * @param explanation Possibly null detail about this exception.
     */
    public SizeLimitExceededException(String explanation) {
        super(explanation);
    }

    /**
     * Use serialVersionUID from JNDI 1.1.1 for interoperability
     */
    private static final long serialVersionUID = 7129289564879168579L;
}
