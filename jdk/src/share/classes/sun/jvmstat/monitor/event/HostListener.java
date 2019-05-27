package sun.jvmstat.monitor.event;

import java.util.EventListener;

/**
 * Interface for listeners of MonitoredHost events.
 */
public interface HostListener extends EventListener {

    /**
     * Invoked when the status of Java Virtual Machine changes.
     *
     * @param event the object describing the event.
     */
    void vmStatusChanged(VmStatusChangeEvent event);

    /**
     * Invoked when the connection to the MonitoredHost has disconnected
     * due to communication errors.
     *
     * @param event the object describing the event.
     */
    void disconnected(HostEvent event);
}
