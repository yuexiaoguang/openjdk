package sun.jvmstat.perfdata.monitor;

import sun.jvmstat.monitor.MonitorException;

/**
 * Exception indicating that improperly formatted data was encountered
 * while parsing a HotSpot PerfData buffer.
 */
public class MonitorStructureException extends MonitorException {

    /**
     * Constructs a <code>MonitorStructureException</code> with <code>
     * null</code> as its error detail message.
     */
     public MonitorStructureException() {
         super();
     }

    /**
     * Constructs an <code>MonitorStructureException</code> with the specified
     * detail message. The error message string <code>s</code> can later be
     * retrieved by the <code>{@link java.lang.Throwable#getMessage}</code>
     * method of class <code>java.lang.Throwable</code>.
     *
     * @param s the detail message.
     */
    public MonitorStructureException(String s) {
        super(s);
    }
}
