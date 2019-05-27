package jdk.internal.org.objectweb.asm.util;

import java.util.Map;

import jdk.internal.org.objectweb.asm.Label;

/**
 * An {@link jdk.internal.org.objectweb.asm.Attribute Attribute} that can print the ASM code
 * to create an equivalent attribute.
 */
public interface ASMifiable {

    /**
     * Prints the ASM code to create an attribute equal to this attribute.
     *
     * @param buf
     *            a buffer used for printing Java code.
     * @param varName
     *            name of the variable in a printed code used to store attribute
     *            instance.
     * @param labelNames
     *            map of label instances to their names.
     */
    void asmify(StringBuffer buf, String varName, Map<Label, String> labelNames);
}
