package sun.awt.windows;

import java.awt.*;
import java.awt.peer.DialogPeer;
import java.awt.peer.ComponentPeer;
import java.awt.dnd.DropTarget;
import java.util.Vector;
import sun.awt.AppContext;
import sun.awt.CausedFocusEvent;
import sun.awt.AWTAccessor;

public class WPrintDialogPeer extends WWindowPeer implements DialogPeer {

    static {
        initIDs();
    }

    private WComponentPeer parent;

    private Vector<WWindowPeer> blockedWindows = new Vector<WWindowPeer>();

    WPrintDialogPeer(WPrintDialog target) {
        super(target);
    }

    void create(WComponentPeer parent) {
        this.parent = parent;
    }

    // fix for CR 6178323:
    // don't use checkCreation() from WComponentPeer to avoid hwnd check
    protected void checkCreation() {
    }

    protected void disposeImpl() {
        WToolkit.targetDisposedPeer(target, this);
    }

    private native boolean _show();

    public void show() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    ((WPrintDialog)target).setRetVal(_show());
                } catch (Exception e) {
                    // No exception should be thrown by native dialog code,
                    // but if it is we need to trap it so the thread does
                    // not hide is called and the thread doesn't hang.
                }
                ((WPrintDialog)target).setVisible(false);
            }
        }).start();
    }

    synchronized void setHWnd(long hwnd) {
        this.hwnd = hwnd;
        for (WWindowPeer window : blockedWindows) {
            if (hwnd != 0) {
                window.modalDisable((Dialog)target, hwnd);
            } else {
                window.modalEnable((Dialog)target);
            }
        }
    }

    synchronized void blockWindow(WWindowPeer window) {
        blockedWindows.add(window);
        if (hwnd != 0) {
            window.modalDisable((Dialog)target, hwnd);
        }
    }
    synchronized void unblockWindow(WWindowPeer window) {
        blockedWindows.remove(window);
        if (hwnd != 0) {
            window.modalEnable((Dialog)target);
        }
    }

    public void blockWindows(java.util.List<Window> toBlock) {
        for (Window w : toBlock) {
            WWindowPeer wp = (WWindowPeer)AWTAccessor.getComponentAccessor().getPeer(w);
            if (wp != null) {
                blockWindow(wp);
            }
        }
    }

    public native void toFront();
    public native void toBack();

    // unused methods.  Overridden to disable this functionality as
    // it requires HWND which is not available for FileDialog
    void initialize() {}
    public void updateAlwaysOnTopState() {}
    public void setResizable(boolean resizable) {}
    public void hide() {}
    public void enable() {}
    public void disable() {}
    public void reshape(int x, int y, int width, int height) {}
    public boolean handleEvent(Event e) { return false; }
    public void setForeground(Color c) {}
    public void setBackground(Color c) {}
    public void setFont(Font f) {}
    public void updateMinimumSize() {}
    public void updateIconImages() {}
    public boolean requestFocus(boolean temporary, boolean focusedWindowChangeAllowed) {
        return false;
    }

    public boolean requestFocus
         (Component lightweightChild, boolean temporary,
          boolean focusedWindowChangeAllowed, long time, CausedFocusEvent.Cause cause)
    {

        return false;
    }

    public void updateFocusableWindowState() {}
    void start() {}
    public void beginValidate() {}
    public void endValidate() {}
    void invalidate(int x, int y, int width, int height) {}
    public void addDropTarget(DropTarget dt) {}
    public void removeDropTarget(DropTarget dt) {}
    public void setZOrder(ComponentPeer above) {}

    /**
     * Initialize JNI field and method ids
     */
    private static native void initIDs();

    // The effects are not supported for system dialogs.
    public void applyShape(sun.java2d.pipe.Region shape) {}
    public void setOpacity(float opacity) {}
    public void setOpaque(boolean isOpaque) {}
    public void updateWindow(java.awt.image.BufferedImage backBuffer) {}

    // the file/print dialogs are native dialogs and
    // the native system does their own rendering
    @Override
    public void createScreenSurface(boolean isResize) {}
    @Override
    public void replaceSurfaceData() {}
}
