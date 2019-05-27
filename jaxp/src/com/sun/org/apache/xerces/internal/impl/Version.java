/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xerces.internal.impl;

/**
 * This class defines the version number of the parser.
 */
public class Version {

    //
    // Data
    //

    /** Version string.
     * @deprecated  getVersion() should be used instead.  */
    public static final String fVersion = getVersion();

    private static final String fImmutableVersion = "Xerces-J 2.7.1";

    // public methods

    /* Print out the version information.
     * @return the version of the parser.
     */
    public static String getVersion() {
        return fImmutableVersion;
    } // getVersion():  String

    //
    // MAIN
    //

    /**
     * Prints out the version number to System.out. This is needed
     * for the build system.
     */
    public static void main(String argv[]) {
        System.out.println(fVersion);
    }

} // class Version
