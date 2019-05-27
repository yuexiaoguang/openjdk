package sun.jvmstat.monitor.event;

import java.util.EventListener;

/**
 * Interface for listeners of MonitoredVm events.
 */
public interface VmListener extends EventListener {

    /**
     * Invoked when instrumentation objects are inserted into or removed
     * from the MonitoredVm.
     *
     * @param event the object describing the event.
     */
    void monitorStatusChanged(MonitorStatusChangeEvent event);

    /**
     * Invoked when instrumentation objects are updated. This event is
     * generated at a fixed interval as determined by the polling rate
     * of the MonitoredVm that the VmListener is registered with.
     *
     * @param event the object describing the event.
     */
    void monitorsUpdated(VmEvent event);

    /**
     * Invoked when the connection to the MonitoredVm has disconnected
     * due to communication errors.
     *
     * @param event the object describing the event.
     */
    void disconnected(VmEvent event);
}
