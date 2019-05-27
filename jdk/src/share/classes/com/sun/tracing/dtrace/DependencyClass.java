package com.sun.tracing.dtrace;

/**
 * Enumeration for the DTrace dependency classes.
 */
public enum DependencyClass {
    /**
     * The interface has an unknown set of architectural dependencies.
     */
    UNKNOWN  (0),
    /**
     * The interface is specific to the CPU model of the current system.
     */
    CPU      (1),
    /**
     * The interface is specific to the hardware platform of the current
     * system.
     */
    PLATFORM (2),
    /**
     * The interface is specific to the hardware platform group of the
     * current system.
     */
    GROUP    (3),
    /**
     * The interface is specific to the instruction set architecture (ISA)
     * supported by the microprocessors on this system.
     */
    ISA      (4),
    /**
     * The interface is common to all Solaris systems regardless of the
     * underlying hardware.
     */
    COMMON   (5);

    public String toDisplayString() {
        return toString().substring(0,1) +
               toString().substring(1).toLowerCase();
    }

    public int getEncoding() { return encoding; }

    private int encoding;

    private DependencyClass(int encoding) {
        this.encoding = encoding;
    }
}

