package sun.awt.X11;

interface XDropTargetProtocolListener {
    void handleDropTargetNotification(XWindow xwindow, int x, int y,
                                      int dropAction, int actions,
                                      long[] formats, long nativeCtxt,
                                      int eventID);
}
