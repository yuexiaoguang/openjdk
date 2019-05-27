package com.sun.jdi;

import java.util.List;

/**
 * A virtual machine which searches for classes through paths
 */
@jdk.Exported
public interface PathSearchingVirtualMachine extends VirtualMachine {
    /**
     * Get the class path for this virtual machine.
     *
     * @return {@link List} of components of the classpath,
     * each represented by a {@link String}.
     */
    List<String> classPath();

    /**
     * Get the boot class path for this virtual machine.
     *
     * @return {@link List} of components of the boot class path,
     * each represented by a {@link String}.
     */
    List<String> bootClassPath();

    /**
     * Get the base directory used for path searching. Relative directories
     * in the class path and boot class path can be resolved through
     * this directory name.
     *
     * @return the base directory.
     */
    String baseDirectory();
}
