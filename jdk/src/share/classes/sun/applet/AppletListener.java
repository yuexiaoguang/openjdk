package sun.applet;

import java.util.EventListener;

/**
 * Applet Listener interface.  This interface is to be implemented
 * by objects interested in AppletEvents.
 */
public interface AppletListener extends EventListener {
    public void appletStateChanged(AppletEvent e);
}
