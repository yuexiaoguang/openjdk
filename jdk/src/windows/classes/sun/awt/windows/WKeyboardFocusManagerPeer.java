package sun.awt.windows;

import java.awt.Window;
import java.awt.Component;
import java.awt.peer.ComponentPeer;
import sun.awt.KeyboardFocusManagerPeerImpl;
import sun.awt.CausedFocusEvent;

class WKeyboardFocusManagerPeer extends KeyboardFocusManagerPeerImpl {
    static native void setNativeFocusOwner(ComponentPeer peer);
    static native Component getNativeFocusOwner();
    static native Window getNativeFocusedWindow();

    private static final WKeyboardFocusManagerPeer inst = new WKeyboardFocusManagerPeer();

    public static WKeyboardFocusManagerPeer getInstance() {
        return inst;
    }

    private WKeyboardFocusManagerPeer() {
    }

    @Override
    public void setCurrentFocusOwner(Component comp) {
        setNativeFocusOwner(comp != null ? comp.getPeer() : null);
    }

    @Override
    public Component getCurrentFocusOwner() {
        return getNativeFocusOwner();
    }

    @Override
    public void setCurrentFocusedWindow(Window win) {
        // Not used on Windows
        throw new RuntimeException("not implemented");
    }

    @Override
    public Window getCurrentFocusedWindow() {
        return getNativeFocusedWindow();
    }

    public static boolean deliverFocus(Component lightweightChild,
                                       Component target,
                                       boolean temporary,
                                       boolean focusedWindowChangeAllowed,
                                       long time,
                                       CausedFocusEvent.Cause cause)
    {
        // TODO: do something to eliminate this forwarding
        return KeyboardFocusManagerPeerImpl.deliverFocus(lightweightChild,
                                                         target,
                                                         temporary,
                                                         focusedWindowChangeAllowed,
                                                         time,
                                                         cause,
                                                         getNativeFocusOwner());
    }
}
