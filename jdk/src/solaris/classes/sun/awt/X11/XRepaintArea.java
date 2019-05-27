package sun.awt.X11;

import java.awt.Component;
import java.awt.Graphics;
import sun.awt.RepaintArea;
import java.awt.peer.ComponentPeer;

/**
 * The <code>RepaintArea</code> is a geometric construct created for the
 * purpose of holding the geometry of several coalesced paint events.
 * This geometry is accessed synchronously, although it is written such
 * that painting may still be executed asynchronously.
 */
class XRepaintArea extends RepaintArea {

    /**
     * Constructs a new <code>XRepaintArea</code>
     * @since   1.3
     */
    public XRepaintArea() {
    }

    /**
     * Calls <code>Component.update(Graphics)</code> with given Graphics.
     */
    protected void updateComponent(Component comp, Graphics g) {
        if (comp != null) {
            final XComponentPeer peer = (XComponentPeer) comp.getPeer();
            if (peer != null) {
                peer.paintPeer(g);
            }
            super.updateComponent(comp, g);
        }
    }

    /**
     * Calls <code>Component.paint(Graphics)</code> with given Graphics.
     */
    protected void paintComponent(Component comp, Graphics g) {
        if (comp != null) {
            final XComponentPeer peer = (XComponentPeer) comp.getPeer();
            if (peer != null) {
                peer.paintPeer(g);
            }
            super.paintComponent(comp, g);
        }
    }
}
