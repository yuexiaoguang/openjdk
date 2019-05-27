package sun.swing;

/**
 * This interface is used only for tagging keys for client properties
 * for {@code JComponent} set by UI which needs to be cleared on L&F
 * change and serialization.
 *
 * All such keys are removed from client properties in {@code
 * JComponent.setUI()} method after uninstalling old UI and before
 * intalling the new one. They are also removed prior to serialization.
 */
public interface UIClientPropertyKey {
}
