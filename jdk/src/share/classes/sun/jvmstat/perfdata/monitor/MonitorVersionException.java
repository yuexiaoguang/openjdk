package sun.jvmstat.perfdata.monitor;

import sun.jvmstat.monitor.MonitorException;

/**
 * Exception thrown when version of the implementation does not
 * match the version of the instrumentation exported by a target
 * Java Virtual Machine.
 */
public class MonitorVersionException extends MonitorException {

    /**
     * Create a MonitorVersionException
     */
    public MonitorVersionException() {
        super();
    }

    /**
     * Create a MonitorVersionException with the given message.
     *
     * @param message the message to associate with the exception.
     */
    public MonitorVersionException(String message) {
        super(message);
    }
}
