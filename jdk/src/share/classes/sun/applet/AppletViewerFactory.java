package sun.applet;

import java.util.Hashtable;
import java.net.URL;
import java.awt.MenuBar;

public
interface AppletViewerFactory {
        public AppletViewer createAppletViewer(int x, int y, URL doc, Hashtable atts);
        public MenuBar getBaseMenuBar();
        public boolean isStandalone();
}
