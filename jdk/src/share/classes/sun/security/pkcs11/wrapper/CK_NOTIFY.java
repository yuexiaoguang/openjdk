package sun.security.pkcs11.wrapper;

/**
 * interface CK_NOTIFY.<p>
 */
public interface CK_NOTIFY {

    /**
     * Method CK_NOTIFY
     *
     * @param hSession
     * @param event
     * @param pApplication
     * @exception PKCS11Exception
     */
    public void CK_NOTIFY(long hSession, long event, Object pApplication) throws PKCS11Exception;

}
