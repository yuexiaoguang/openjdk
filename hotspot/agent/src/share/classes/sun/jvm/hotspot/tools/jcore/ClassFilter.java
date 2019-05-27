package sun.jvm.hotspot.tools.jcore;

import sun.jvm.hotspot.oops.InstanceKlass;

public interface ClassFilter
{
    public boolean canInclude(InstanceKlass kls);
}
