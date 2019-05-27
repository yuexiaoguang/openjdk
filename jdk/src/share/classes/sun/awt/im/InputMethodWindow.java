package sun.awt.im;

/**
 * Interface for input method windows that need special handling
 * by input method window.
 */
public interface InputMethodWindow {

    /**
     * Sets the input context that this input method window is attached to,
     * null to unattach the window.
     */
    public void setInputContext(InputContext inputContext);

}
