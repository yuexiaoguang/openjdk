package javax.swing.plaf;

import javax.swing.JComboBox;

/**
 * Pluggable look and feel interface for JComboBox.
 */
public abstract class ComboBoxUI extends ComponentUI {

    /**
     * Set the visibility of the popup
     */
    public abstract void setPopupVisible( JComboBox c, boolean v );

    /**
     * Determine the visibility of the popup
     */
    public abstract boolean isPopupVisible( JComboBox c );

    /**
     * Determine whether or not the combo box itself is traversable
     */
    public abstract boolean isFocusTraversable( JComboBox c );
}
