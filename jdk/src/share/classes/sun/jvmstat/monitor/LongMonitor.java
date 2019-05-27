package sun.jvmstat.monitor;

/**
 * Interface for Monitoring LongInstrument objects.
 */
public interface LongMonitor extends Monitor {

    /**
     * Get the current value of this LongInstrument object.
     *
     * @return long - the current value of the associated LongInstrument object.
     */
    public long longValue();
}
