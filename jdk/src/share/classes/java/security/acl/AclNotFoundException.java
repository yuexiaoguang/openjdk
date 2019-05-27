package java.security.acl;

/**
 * This is an exception that is thrown whenever a reference is made to a
 * non-existent ACL (Access Control List).
 */
public class AclNotFoundException extends Exception {

    private static final long serialVersionUID = 5684295034092681791L;

    /**
     * Constructs an AclNotFoundException.
     */
    public AclNotFoundException() {
    }

}
