package sun.jvmstat.monitor.event;

import java.util.EventObject;
import sun.jvmstat.monitor.MonitoredHost;

/**
 * Base class for events emitted by a {@link MonitoredHost}.
 */
public class HostEvent extends EventObject {

    /**
     * Construct a new HostEvent instance.
     *
     * @param host the MonitoredHost source of the event.
     */
    public HostEvent(MonitoredHost host) {
        super(host);
    }

    /**
     * Return the MonitoredHost source of this event.
     *
     * @return MonitoredHost - the source of this event.
     */
    public MonitoredHost getMonitoredHost() {
        return (MonitoredHost)source;
    }
}
