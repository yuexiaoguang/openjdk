package sun.swing;

import javax.swing.Icon;
import javax.swing.JMenuItem;

public interface MenuItemCheckIconFactory {
    Icon getIcon(JMenuItem component);
    boolean isCompatible(Object icon, String prefix);
}
