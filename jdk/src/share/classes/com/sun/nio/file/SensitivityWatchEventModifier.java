package com.sun.nio.file;

import java.nio.file.WatchEvent.Modifier;

/**
 * Defines the <em>sensitivity levels</em> when registering objects with a
 * watch service implementation that polls the file system.
 */
public enum SensitivityWatchEventModifier implements Modifier {
    /**
     * High sensitivity.
     */
    HIGH(2),
    /**
     * Medium sensitivity.
     */
    MEDIUM(10),
    /**
     * Low sensitivity.
     */
    LOW(30);

    /**
     * Returns the sensitivity in seconds.
     */
    public int sensitivityValueInSeconds() {
        return sensitivity;
    }

    private final int sensitivity;
    private SensitivityWatchEventModifier(int sensitivity) {
        this.sensitivity = sensitivity;
    }
}
