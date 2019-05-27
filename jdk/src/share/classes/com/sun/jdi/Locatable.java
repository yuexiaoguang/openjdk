package com.sun.jdi;

/**
 * A mirror that has a {@link Location}.
 */
@jdk.Exported
public interface Locatable {
    /**
     * Returns the {@link Location} of this mirror, if there
     * is executable code associated with it. Note that both
     * Java<SUP><FONT SIZE="-2">TM</FONT></SUP> programming
     * language methods and native methods have executable code.
     * Returns null for abstract methods, since abstract methods
     * have no executable code.
     *
     * @return the {@link Location} of this mirror, or null if
     * there is no executable code associated with it.
     */
    Location location();
}
