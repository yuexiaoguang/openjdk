package sun.security.pkcs11.wrapper;



/**
 * interface CK_LOCKMUTEX<p>
 */
public interface CK_LOCKMUTEX {

    /**
     * Method CK_LOCKMUTEX
     *
     * @param pMutex The mutex (lock) object to lock.
     * @exception PKCS11Exception
     */
    public void CK_LOCKMUTEX(Object pMutex) throws PKCS11Exception;

}
