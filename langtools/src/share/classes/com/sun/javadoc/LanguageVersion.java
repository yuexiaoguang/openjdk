package com.sun.javadoc;


/**
 * Java Programming Language version.  The constants of this enum
 * identify the JDK and J2SE releases containing language changes
 * relevant to doclets.
 * <p>
 * All doclets support at least the 1.1 language version.
 * The first release subsequent to this with language changes
 * affecting doclets is 1.5.
 */
public enum LanguageVersion {

    /** 1.1 added nested classes and interfaces. */
    JAVA_1_1,

    /** 1.5 added generic types, annotations, enums, and varArgs. */
    JAVA_1_5
}
