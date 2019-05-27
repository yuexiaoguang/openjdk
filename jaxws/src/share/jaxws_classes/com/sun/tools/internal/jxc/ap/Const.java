package com.sun.tools.internal.jxc.ap;

import java.io.File;

/**
 * Defines constants used in the Annotation Processing driver.
 */
public enum Const {

    /**
     * Name of the annotation processing command-line option to take user-specified config files.
     * <p/>
     * <p/>
     * It can take multiple file names separately by {@link File#pathSeparator}.
     */
    CONFIG_FILE_OPTION("jaxb.config"),

    DEBUG_OPTION("jaxb.debug");

    private String value;

    private Const(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
