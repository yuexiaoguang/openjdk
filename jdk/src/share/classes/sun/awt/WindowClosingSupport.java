package sun.awt;

/**
 * Interface for identifying and casting toolkits that support
 * WindowClosingListeners.
 */
public interface WindowClosingSupport {
    WindowClosingListener getWindowClosingListener();
    void setWindowClosingListener(WindowClosingListener wcl);
}
