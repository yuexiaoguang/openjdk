package com.sun.tools.internal.xjc.api;

/**
 * Represents the spec version constant.
 */
public enum SpecVersion {
    V2_0, V2_1, V2_2;

    /**
     * Returns true if this version is equal or later than the given one.
     */
    public boolean isLaterThan(SpecVersion t) {
        return this.ordinal()>=t.ordinal();
    }

    /**
     * Parses "2.0", "2.1", and "2.2" into the {@link SpecVersion} object.
     *
     * @return null for parsing failure.
     */
    public static SpecVersion parse(String token) {
        if(token.equals("2.0"))
            return V2_0;
        if(token.equals("2.1"))
            return V2_1;
        if(token.equals("2.2"))
            return V2_2;
        return null;
    }

    public static final SpecVersion LATEST = V2_2;
}
