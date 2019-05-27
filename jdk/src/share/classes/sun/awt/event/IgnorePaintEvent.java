package sun.awt.event;

import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.PaintEvent;

/**
 * PaintEvents that are effectively ignored.  This class is used only for
 * tagging.  If a heavy weight peer is asked to handle an event of this
 * class it'll ignore it.  This class is used by Swing.
 * Look at <code>javax.swing.SwingPaintEventDispatcher</code> for more.
 */
public class IgnorePaintEvent extends PaintEvent {
    public IgnorePaintEvent(Component source, int id, Rectangle updateRect) {
        super(source, id, updateRect);
    }
}
