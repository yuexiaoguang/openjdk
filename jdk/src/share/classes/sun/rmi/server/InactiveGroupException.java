package sun.rmi.server;

import java.rmi.activation.ActivationException;

/**
 * Thrown if a local or remote call is made on a group implementation
 * instance that is inactive.
 */
public class InactiveGroupException extends ActivationException {

    private static final long serialVersionUID = -7491041778450214975L;

    /**
     * Constructs an instance with the specified detail message.
     *
     * @param s the detail message
     */
    public InactiveGroupException(String s) {
        super(s);
    }
}
