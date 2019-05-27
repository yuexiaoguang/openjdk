package javax.swing.event;


import java.util.EventListener;


/**
 * The listener that's notified when a lists selection value
 * changes.
 */
public interface ListSelectionListener extends EventListener
{
  /**
   * Called whenever the value of the selection changes.
   * @param e the event that characterizes the change.
   */
  void valueChanged(ListSelectionEvent e);
}
