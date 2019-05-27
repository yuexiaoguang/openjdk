package com.sun.management;

import javax.management.openmbean.CompositeData;
import javax.management.openmbean.CompositeType;

/**
 * Platform-specific management interface for a garbage collector
 * which performs collections in cycles.
 *
 * <p> This platform extension is only available to the garbage
 * collection implementation that supports this extension.
 */
@jdk.Exported
public interface GarbageCollectorMXBean
    extends java.lang.management.GarbageCollectorMXBean {

    /**
     * Returns the GC information about the most recent GC.
     * This method returns a {@link GcInfo}.
     * If no GC information is available, <tt>null</tt> is returned.
     * The collector-specific attributes, if any, can be obtained
     * via the {@link CompositeData CompositeData} interface.
     * <p>
     * <b>MBeanServer access:</b>
     * The mapped type of <tt>GcInfo</tt> is <tt>CompositeData</tt>
     * with attributes specified in {@link GcInfo#from GcInfo}.
     *
     * @return a <tt>GcInfo</tt> object representing
     * the most GC information; or <tt>null</tt> if no GC
     * information available.
     */
    public GcInfo getLastGcInfo();
}
