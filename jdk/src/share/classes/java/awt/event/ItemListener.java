package java.awt.event;

import java.util.EventListener;

/**
 * The listener interface for receiving item events.
 * The class that is interested in processing an item event
 * implements this interface. The object created with that
 * class is then registered with a component using the
 * component's <code>addItemListener</code> method. When an
 * item-selection event occurs, the listener object's
 * <code>itemStateChanged</code> method is invoked.
 */
public interface ItemListener extends EventListener {

    /**
     * Invoked when an item has been selected or deselected by the user.
     * The code written for this method performs the operations
     * that need to occur when an item is selected (or deselected).
     */
    void itemStateChanged(ItemEvent e);

}
