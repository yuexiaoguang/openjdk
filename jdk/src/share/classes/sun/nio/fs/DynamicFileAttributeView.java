package sun.nio.fs;

import java.util.Map;
import java.io.IOException;

/**
 * Implemented by FileAttributeView implementations to support access to
 * attributes by names.
 */
interface DynamicFileAttributeView {
    /**
     * Sets/updates the value of an attribute.
     */
    void setAttribute(String attribute, Object value) throws IOException;

    /**
     * Reads a set of file attributes as a bulk operation.
     */
    Map<String,Object> readAttributes(String[] attributes) throws IOException;
}
