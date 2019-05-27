package javax.transaction;

/**
 * This exception indicates that the request carried an invalid transaction
 * context. For example, this exception could be raised if an error
 * occured when trying to register a resource.
 */
public class InvalidTransactionException extends java.rmi.RemoteException
{
    public InvalidTransactionException()
    {
        super();
    }

    public InvalidTransactionException(String msg)
    {
        super(msg);
    }
}
