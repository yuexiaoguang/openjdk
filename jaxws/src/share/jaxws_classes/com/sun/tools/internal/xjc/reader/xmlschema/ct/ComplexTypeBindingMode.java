package com.sun.tools.internal.xjc.reader.xmlschema.ct;

/**
 * Three-state flag for a complex type.
 */
public enum ComplexTypeBindingMode {

    /**
     * Neither FALLBACK nor NOMOREEXTENSION.
     */
    NORMAL,

    /**
     * If a complex type has falled back to the general list content and
     * it is not NOMOREEXTENSION.
     */
    FALLBACK_CONTENT,

    /**
     * If a complex type has falled back to the rest content and
     * it is not NOMOREEXTENSION.
     */
    FALLBACK_REST,

    /**
     * If a complex type has fallen to the dummy property in order
     * to override previously inherited content.
     */
    FALLBACK_EXTENSION

//
//    /**
//     * If a complex type is derived by restriction from a complex type
//     * other than the ur-type. Once this flag is turned on, no more
//     * derivation by extension is allowed.
//     */
//    static final ComplexTypeBindingMode NOMOREEXTENSION = new ComplexTypeBindingMode("noMoreExtension");
}
