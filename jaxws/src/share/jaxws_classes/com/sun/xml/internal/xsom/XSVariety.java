package com.sun.xml.internal.xsom;

/**
 * Constants that represent variety of simple types.
 */
public final class XSVariety {
    public static final XSVariety ATOMIC = new XSVariety("atomic");
    public static final XSVariety UNION  = new XSVariety("union");
    public static final XSVariety LIST   = new XSVariety("list");

    private XSVariety(String _name) { this.name=_name; }
    private final String name;
    public String toString() { return name; }
}
