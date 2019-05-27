package com.sun.java.swing.plaf.windows;

import java.beans.PropertyChangeEvent;

import javax.swing.*;
import javax.swing.plaf.basic.*;

/**
 * Button Listener
 * <p>
 * <strong>Warning:</strong>
 * Serialized objects of this class will not be compatible with
 * future Swing releases.  The current serialization support is appropriate
 * for short term storage or RMI between applications running the same
 * version of Swing.  A future release of Swing will provide support for
 * long term persistence.
 */
public class WindowsButtonListener extends BasicButtonListener {
    public WindowsButtonListener(AbstractButton b) {
        super(b);
    }
    /*
     This class is currently not used, but exists in case customers
     were subclassing it.
     */
}
