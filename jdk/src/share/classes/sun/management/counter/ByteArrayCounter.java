package sun.management.counter;

/**
 * Interface for performance counter wrapping <code>byte[]</code> objects.
 */
public interface ByteArrayCounter extends Counter {

    /**
     * Get a copy of the elements of the ByteArrayCounter.
     */
    public byte[] byteArrayValue();

    /**
     * Get the value of an element of the ByteArrayCounter object.
     */
    public byte byteAt(int index);
}
