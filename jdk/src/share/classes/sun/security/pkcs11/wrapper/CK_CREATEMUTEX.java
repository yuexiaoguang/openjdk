package sun.security.pkcs11.wrapper;

/**
 * interface CK_CREATEMUTEX.<p>
 */
public interface CK_CREATEMUTEX {

    /**
     * Method CK_CREATEMUTEX
     *
     * @param ppMutex
     * @return The mutex (lock) object.
     * @exception PKCS11Exception
     */
    public Object CK_CREATEMUTEX() throws PKCS11Exception;

}
