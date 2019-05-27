package sun.jvmstat.monitor.event;

import java.util.EventObject;
import sun.jvmstat.monitor.MonitoredVm;

/**
 * Base class for events emitted by a {@link MonitoredVm}.
 */
public class VmEvent extends EventObject {

    /**
     * Construct a new VmEvent instance.
     *
     * @param vm the MonitoredVm source of the event.
     */
    public VmEvent(MonitoredVm vm) {
        super(vm);
    }

    /**
     * Return the MonitoredVm source of this event.
     *
     * @return MonitoredVm - the source of this event.
     */
    public MonitoredVm getMonitoredVm() {
      return (MonitoredVm)source;
    }
}
