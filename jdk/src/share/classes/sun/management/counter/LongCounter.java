package sun.management.counter;

/**
 * Interface for a performance counter wrapping a
 * <code>long</code> basic type.
 */
public interface LongCounter extends Counter {

    /**
     * Get the value of this Long performance counter
     */
    public long longValue();
}
