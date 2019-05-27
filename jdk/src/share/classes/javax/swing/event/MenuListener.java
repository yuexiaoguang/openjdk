package javax.swing.event;


import java.util.EventListener;


/**
 * Defines a listener for menu events.
 */
public interface MenuListener extends EventListener {
    /**
     * Invoked when a menu is selected.
     *
     * @param e  a MenuEvent object
     */
    void menuSelected(MenuEvent e);
    /**
     * Invoked when the menu is deselected.
     *
     * @param e  a MenuEvent object
     */
    void menuDeselected(MenuEvent e);
    /**
     * Invoked when the menu is canceled.
     *
     * @param e  a MenuEvent object
     */
    void menuCanceled(MenuEvent e);
}
