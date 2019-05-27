package sun.awt;

import java.awt.event.WindowEvent;

/**
 * Interface for listening to WINDOW_CLOSING events before and
 * after they are posted to the queue.
 */
public interface WindowClosingListener {
    /**
     * Called before a WINDOW_CLOSING event gets posted to the queue.
     * @param event The WINDOW_CLOSING event that will be posted.
     * @return A RuntimeException if the result of this function
     * call needs to interrupt a blocking thread.  The exception
     * returned will be thrown from that thread.  This function
     * should return null otherwise.
     */
    RuntimeException windowClosingNotify(WindowEvent event);
    /**
     * Called after a WINDOW_CLOSING event gets posted to the queue.
     * @param event The WINDOW_CLOSING event that has been posted.
     * @return A RuntimeException if the result of this function
     * call needs to interrupt a blocking thread.  The exception
     * returned will be thrown from that thread.  This function
     * should return null otherwise.
     */
    RuntimeException windowClosingDelivered(WindowEvent event);
}
