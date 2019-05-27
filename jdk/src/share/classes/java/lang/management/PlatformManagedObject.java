package java.lang.management;

import javax.management.ObjectName;

/**
 * A platform managed object is a {@linkplain javax.management.MXBean JMX MXBean}
 * for monitoring and managing a component in the Java platform.
 * Each platform managed object has a unique
 * <a href="ManagementFactory.html#MXBean">object name</a>
 * for the {@linkplain ManagementFactory#getPlatformMBeanServer
 * platform MBeanServer} access.
 * All platform MXBeans will implement this interface.
 *
 * <p>
 * Note:
 * The platform MXBean interfaces (i.e. all subinterfaces
 * of {@code PlatformManagedObject}) are implemented
 * by the Java platform only.  New methods may be added in these interfaces
 * in future Java SE releases.
 * In addition, this {@code PlatformManagedObject} interface is only
 * intended for the management interfaces for the platform to extend but
 * not for applications.
 */
public interface PlatformManagedObject {
    /**
     * Returns an {@link ObjectName ObjectName} instance representing
     * the object name of this platform managed object.
     *
     * @return an {@link ObjectName ObjectName} instance representing
     * the object name of this platform managed object.
     */
    public ObjectName getObjectName();
}
