package sun.jvmstat.perfdata.monitor;

import sun.jvmstat.monitor.MonitorException;

/**
 * Exception indicating that an unexpected type was encountered for
 * the type field of a PerfData entry in a HotSpot PerfData buffer.
 */
public class MonitorTypeException extends MonitorException {

    /**
     * Constructs a <code>MonitorTypeException</code> with <code>
     * null </code> as its error detail message.
     */
     public MonitorTypeException() {
         super();
     }

    /**
     * Constructs an <code>MonitorTypeException</code> with the specified
     * detail message. The error message string <code>s</code> can later be
     * retrieved by the <code>{@link java.lang.Throwable#getMessage}</code>
     * method of class <code>java.lang.Throwable</code>.
     *
     * @param s the detail message.
     */
    public MonitorTypeException(String s) {
        super(s);
    }
}
