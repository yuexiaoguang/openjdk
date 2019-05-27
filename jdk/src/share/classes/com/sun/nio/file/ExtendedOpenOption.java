package com.sun.nio.file;

import java.nio.file.OpenOption;

/**
 * Defines <em>extended</em> open options supported on some platforms
 * by Sun's provider implementation.
 */
public enum ExtendedOpenOption implements OpenOption {
    /**
     * Prevent operations on the file that request read access.
     */
    NOSHARE_READ,
    /**
     * Prevent operations on the file that request write access.
     */
    NOSHARE_WRITE,
    /**
     * Prevent operations on the file that request delete access.
     */
    NOSHARE_DELETE;
}
