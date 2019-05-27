package javax.swing.event;

import java.util.EventListener;

/**
 * The listener that's notified when the selection in a TreeSelectionModel
 * changes.
 * For more information and examples see
 * <a
 href="http://docs.oracle.com/javase/tutorial/uiswing/events/treeselectionlistener.html">How to Write a Tree Selection Listener</a>,
 * a section in <em>The Java Tutorial.</em>
 */
public interface TreeSelectionListener extends EventListener
{
    /**
      * Called whenever the value of the selection changes.
      * @param e the event that characterizes the change.
      */
    void valueChanged(TreeSelectionEvent e);
}
