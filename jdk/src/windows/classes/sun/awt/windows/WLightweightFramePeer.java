package sun.awt.windows;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;

import sun.awt.LightweightFrame;
import sun.swing.JLightweightFrame;
import sun.swing.SwingAccessor;

public class WLightweightFramePeer extends WFramePeer {

    public WLightweightFramePeer(LightweightFrame target) {
        super(target);
    }

    private LightweightFrame getLwTarget() {
        return (LightweightFrame)target;
    }

    @Override
    public Graphics getGraphics() {
        return getLwTarget().getGraphics();
    }

    @Override
    public void show() {
        super.show();
        postEvent(new ComponentEvent((Component)getTarget(), ComponentEvent.COMPONENT_SHOWN));
    }

    @Override
    public void hide() {
        super.hide();
        postEvent(new ComponentEvent((Component)getTarget(), ComponentEvent.COMPONENT_HIDDEN));
    }

    @Override
    public void reshape(int x, int y, int width, int height) {
        super.reshape(x, y, width, height);
        postEvent(new ComponentEvent((Component) getTarget(), ComponentEvent.COMPONENT_MOVED));
        postEvent(new ComponentEvent((Component) getTarget(), ComponentEvent.COMPONENT_RESIZED));
    }

    @Override
    public void handleEvent(java.awt.AWTEvent e) {
        if (e.getID() == MouseEvent.MOUSE_PRESSED) {
            emulateActivation(true);
        }
        super.handleEvent(e);
    }

    @Override
    public void grab() {
        getLwTarget().grabFocus();
    }

    @Override
    public void ungrab() {
        getLwTarget().ungrabFocus();
    }

    @Override
    public void updateCursorImmediately() {
        SwingAccessor.getJLightweightFrameAccessor().updateCursor((JLightweightFrame)getLwTarget());
    }

    public boolean isLightweightFramePeer() {
        return true;
    }
}
