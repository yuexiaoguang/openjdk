package sun.management.snmp.jvmmib;

// jmx imports
//
import com.sun.jmx.snmp.SnmpStatusException;

/**
 * This interface is used for representing the remote management interface for the "JvmRTLibraryPathEntry" MBean.
 */
public interface JvmRTLibraryPathEntryMBean {

    /**
     * Getter for the "JvmRTLibraryPathItem" variable.
     */
    public String getJvmRTLibraryPathItem() throws SnmpStatusException;

    /**
     * Getter for the "JvmRTLibraryPathIndex" variable.
     */
    public Integer getJvmRTLibraryPathIndex() throws SnmpStatusException;

}
