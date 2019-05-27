package sun.security.pkcs11.wrapper;



/**
 * interface CK_UNLOCKMUTEX<p>
 */
public interface CK_UNLOCKMUTEX {

    /**
     * Method CK_UNLOCKMUTEX
     *
     * @param pMutex The mutex (lock) object to unlock.
     * @exception PKCS11Exception
     */
    public void CK_UNLOCKMUTEX(Object pMutex) throws PKCS11Exception;

}
