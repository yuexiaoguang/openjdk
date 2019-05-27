package sun.jvmstat.monitor;

/**
 * Interface for Monitoring StringInstrument objects.
 */
public interface StringMonitor extends Monitor {

    /**
     * Get a copy of the current value of the StringInstrument object.
     *
     * @return String - a String object containing a copy of the value of
     *                  the associated StringInstrument.
     */
    public String stringValue();
}
