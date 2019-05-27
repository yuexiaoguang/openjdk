package sun.awt.X11;

/**
 * Interface representing abstract event dispatchers in the system.
 * Any class implementing this interface can be installed to receive
 * event on particular window.
 */
public interface XEventDispatcher {
    void dispatchEvent(XEvent ev);
}
