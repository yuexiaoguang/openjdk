package com.sun.tools.doclets.internal.toolkit.util;

/**
 * Enum representing method types.
 */
public enum MethodTypes {
    ALL(0xffff, "All Methods", "t0", true),
    STATIC(0x1, "Static Methods", "t1", false),
    INSTANCE(0x2, "Instance Methods", "t2", false),
    ABSTRACT(0x4, "Abstract Methods", "t3", false),
    CONCRETE(0x8, "Concrete Methods", "t4", false),
    DEFAULT(0x10, "Default Methods", "t5", false),
    DEPRECATED(0x20, "Deprecated Methods", "t6", false);

    private final int value;
    private final String text;
    private final String tabId;
    private final boolean isDefaultTab;

    MethodTypes(int v, String t, String id, boolean dt) {
        this.value = v;
        this.text = t;
        this.tabId = id;
        this.isDefaultTab = dt;
    }

    public int value() {
        return value;
    }

    public String text() {
        return text;
    }

    public String tabId() {
        return tabId;
    }

    public boolean isDefaultTab() {
        return isDefaultTab;
    }
}
