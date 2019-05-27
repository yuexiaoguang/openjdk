package sun.misc;

/*
 * Exception when installation of an extension has failed for
 * any reason
 */
public class ExtensionInstallationException extends Exception {

    static final long serialVersionUID = 3139688306909345924L;

    /*
     * <p>
     * Construct a new exception with an exception reason
     * </p>
     */
    public ExtensionInstallationException(String s) {
        super(s);
    }
}
