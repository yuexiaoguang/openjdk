package com.sun.nio.file;

import java.nio.file.WatchEvent.Modifier;

/**
 * Defines <em>extended</em> watch event modifiers supported on some platforms
 * by Sun's provider implementation.
 */
public enum ExtendedWatchEventModifier implements Modifier {

    /**
     * Register a file tree instead of a single directory.
     */
    FILE_TREE,
}
