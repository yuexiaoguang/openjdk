package sun.jvmstat.monitor;

/**
 * Interface for Monitoring Integer Instrument Objects.
 *
 * The IntegerMonitor interface does not currently have a IntInstrument
 * counterpart. It is used in limited situations to expose certain
 * implementation specifics as performance counters. Typically,
 * a LongInstrument serves as a reasonable replacement for the
 * an IntInstrument class.
 */
public interface IntegerMonitor extends Monitor {

    /**
     * Get the value of this Integer Instrumentation Object
     *
     * return int - the current value of this instrumentation object
     */
    public int intValue();
}
