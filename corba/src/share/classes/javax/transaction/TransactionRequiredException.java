package javax.transaction;

/**
 * This exception indicates that a request carried a null transaction context,
 * but the target object requires an activate transaction.
 */
public class TransactionRequiredException extends java.rmi.RemoteException
{
    public TransactionRequiredException()
    {
        super();
    }

    public TransactionRequiredException(String msg)
    {
        super(msg);
    }
}
