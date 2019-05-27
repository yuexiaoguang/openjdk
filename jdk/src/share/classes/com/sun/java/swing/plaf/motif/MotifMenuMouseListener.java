package com.sun.java.swing.plaf.motif;

import java.awt.event.*;
import javax.swing.MenuSelectionManager;

/**
 * A default MouseListener for menu elements
 */
class MotifMenuMouseListener extends MouseAdapter {
    public void mousePressed(MouseEvent e) {
        MenuSelectionManager.defaultManager().processMouseEvent(e);
    }
    public void mouseReleased(MouseEvent e) {
        MenuSelectionManager.defaultManager().processMouseEvent(e);
    }
    public void mouseEntered(MouseEvent e) {
        MenuSelectionManager.defaultManager().processMouseEvent(e);
    }
    public void mouseExited(MouseEvent e) {
        MenuSelectionManager.defaultManager().processMouseEvent(e);
    }
}
