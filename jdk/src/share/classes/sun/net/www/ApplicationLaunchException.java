package sun.net.www;

/**
 * An exception thrown by the MimeLauncher when it is unable to launch
 * an external content viewer.
 */
public class ApplicationLaunchException extends Exception {
    private static final long serialVersionUID = -4782286141289536883L;

    public ApplicationLaunchException(String reason) {
        super(reason);
    }
}
