package sun.awt.datatransfer;

public interface ToolkitThreadBlockedHandler {
    public void lock();
    public void unlock();
    public void enter();
    public void exit();
}
