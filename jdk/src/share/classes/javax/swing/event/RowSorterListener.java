package javax.swing.event;

/**
 * <code>RowSorterListener</code>s are notified of changes to a
 * <code>RowSorter</code>.
 */
public interface RowSorterListener extends java.util.EventListener {
    /**
     * Notification that the <code>RowSorter</code> has changed.  The event
     * describes the scope of the change.
     *
     * @param e the event, will not be null
     */
    public void sorterChanged(RowSorterEvent e);
}
