package sun.awt.windows;

import java.awt.*;
import java.awt.peer.*;

class WMenuBarPeer extends WMenuPeer implements MenuBarPeer {

    // MenuBarPeer implementation

    public native void addMenu(Menu m);
    public native void delMenu(int index);

    public void addHelpMenu(Menu m) {
        addMenu(m);
    }

    // Toolkit & peer internals
    WMenuBarPeer(MenuBar target) {
        this.target = target;
        WFramePeer framePeer = (WFramePeer)
            WToolkit.targetToPeer(target.getParent());
        create(framePeer);
        // fix for 5088782: check if menu object is created successfully
        checkMenuCreation();
    }
    native void create(WFramePeer f);
}
