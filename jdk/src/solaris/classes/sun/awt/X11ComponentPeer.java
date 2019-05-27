package sun.awt;

import java.awt.Rectangle;
import java.awt.GraphicsConfiguration;
import java.awt.image.ColorModel;
import sun.java2d.SurfaceData;
import java.awt.Graphics;

public interface X11ComponentPeer {
    long getContentWindow();
    SurfaceData getSurfaceData();
    GraphicsConfiguration getGraphicsConfiguration();
    ColorModel getColorModel();
    Rectangle getBounds();
    Graphics getGraphics();
    Object getTarget();
    void setFullScreenExclusiveModeState(boolean state);
}
