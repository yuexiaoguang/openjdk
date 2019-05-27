package java.awt.event;

import java.util.EventListener;

/**
 * The listener interface for receiving adjustment events.
 */
public interface AdjustmentListener extends EventListener {

    /**
     * Invoked when the value of the adjustable has changed.
     */
    public void adjustmentValueChanged(AdjustmentEvent e);

}
