package sun.management.counter;

/**
 * Interface for a performance counter wrapping a <code>String</code> object.
 */
public interface StringCounter extends Counter {

    /**
     * Get a copy of the value of the StringCounter.
     */
    public String stringValue();
}
