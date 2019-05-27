package jdk.internal.org.objectweb.asm.util;

import java.util.Map;

import jdk.internal.org.objectweb.asm.Label;

/**
 * An {@link jdk.internal.org.objectweb.asm.Attribute Attribute} that can print a readable
 * representation of itself.
 *
 * Implementations should construct readable output from an attribute data
 * structure. Such representation could be used in unit test assertions.
 */
public interface Textifiable {

    /**
     * Build a human readable representation of this attribute.
     *
     * @param buf
     *            a buffer used for printing Java code.
     * @param labelNames
     *            map of label instances to their names.
     */
    void textify(StringBuffer buf, Map<Label, String> labelNames);
}
