package javax.swing.event;


import java.util.EventListener;

/**
 * HyperlinkListener
 */
public interface HyperlinkListener extends EventListener {

    /**
     * Called when a hypertext link is updated.
     *
     * @param e the event responsible for the update
     */
    void hyperlinkUpdate(HyperlinkEvent e);
}
