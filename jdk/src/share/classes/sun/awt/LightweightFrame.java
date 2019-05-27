package sun.awt;

import java.awt.Container;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MenuBar;
import java.awt.MenuComponent;
import java.awt.Toolkit;
import java.awt.peer.FramePeer;

/**
 * The class provides basic functionality for a lightweight frame
 * implementation. A subclass is expected to provide painting to an
 * offscreen image and access to it. Thus it can be used for lightweight
 * embedding.
 */
@SuppressWarnings("serial")
public abstract class LightweightFrame extends Frame {

    /**
     * Constructs a new, initially invisible {@code LightweightFrame}
     * instance.
     */
    public LightweightFrame() {
        setUndecorated(true);
        setResizable(true);
        setEnabled(true);
    }

    /**
     * Blocks introspection of a parent window by this child.
     *
     * @return null
     */
    @Override public final Container getParent() { return null; }

    @Override public Graphics getGraphics() { return null; }

    @Override public final boolean isResizable() { return true; }

    // Block modification of any frame attributes, since they aren't
    // applicable for a lightweight frame.

    @Override public final void setTitle(String title) {}
    @Override public final void setIconImage(Image image) {}
    @Override public final void setIconImages(java.util.List<? extends Image> icons) {}
    @Override public final void setMenuBar(MenuBar mb) {}
    @Override public final void setResizable(boolean resizable) {}
    @Override public final void remove(MenuComponent m) {}
    @Override public final void toFront() {}
    @Override public final void toBack() {}

    @Override public void addNotify() {
        synchronized (getTreeLock()) {
            if (getPeer() == null) {
                SunToolkit stk = (SunToolkit)Toolkit.getDefaultToolkit();
                try {
                    setPeer(stk.createLightweightFrame(this));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            super.addNotify();
        }
    }

    private void setPeer(final FramePeer p) {
        AWTAccessor.getComponentAccessor().setPeer(this, p);
    }

    /**
     * Requests the peer to emulate activation or deactivation of the
     * frame. Peers should override this method if they are to implement
     * this functionality.
     *
     * @param activate if <code>true</code>, activates the frame;
     *                 otherwise, deactivates the frame
     */
    public void emulateActivation(boolean activate) {
        ((FramePeer)getPeer()).emulateActivation(activate);
    }

    /**
     * Delegates the focus grab action to the client (embedding) application.
     * The method is called by the AWT grab machinery.
     *
     * @see SunToolkit#grab(java.awt.Window)
     */
    public abstract void grabFocus();

    /**
     * Delegates the focus ungrab action to the client (embedding) application.
     * The method is called by the AWT grab machinery.
     *
     * @see SunToolkit#ungrab(java.awt.Window)
     */
    public abstract void ungrabFocus();
}
