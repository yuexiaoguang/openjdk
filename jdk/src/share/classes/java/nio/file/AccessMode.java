package java.nio.file;

/**
 * Defines access modes used to test the accessibility of a file.
 */
public enum AccessMode {
    /**
     * Test read access.
     */
    READ,
    /**
     * Test write access.
     */
    WRITE,
    /**
     * Test execute access.
     */
    EXECUTE;
}
