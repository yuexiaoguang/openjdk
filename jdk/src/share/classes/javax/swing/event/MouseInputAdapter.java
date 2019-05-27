package javax.swing.event;

import java.awt.event.MouseAdapter;

/**
 * An empty implementation of the {@code MouseInputListener} interface, provided
 * as a convenience to simplify the task of creating listeners, by extending
 * and implementing only the methods of interest. This class also provides an
 * empty implementation of the {@code MouseWheelListener} interface, through
 * its extension from AWT's {@code MouseAdapter}.
 */
public abstract class MouseInputAdapter extends MouseAdapter
                                        implements MouseInputListener {
}
