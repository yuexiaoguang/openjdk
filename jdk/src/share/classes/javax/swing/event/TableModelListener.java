package javax.swing.event;

import java.util.EventListener;

/**
 * TableModelListener defines the interface for an object that listens
 * to changes in a TableModel.
 */
public interface TableModelListener extends java.util.EventListener
{
    /**
     * This fine grain notification tells listeners the exact range
     * of cells, rows, or columns that changed.
     */
    public void tableChanged(TableModelEvent e);
}
