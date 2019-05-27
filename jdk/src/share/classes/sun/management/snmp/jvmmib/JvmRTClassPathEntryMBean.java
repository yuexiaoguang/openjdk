package sun.management.snmp.jvmmib;

// jmx imports
//
import com.sun.jmx.snmp.SnmpStatusException;

/**
 * This interface is used for representing the remote management interface for the "JvmRTClassPathEntry" MBean.
 */
public interface JvmRTClassPathEntryMBean {

    /**
     * Getter for the "JvmRTClassPathItem" variable.
     */
    public String getJvmRTClassPathItem() throws SnmpStatusException;

    /**
     * Getter for the "JvmRTClassPathIndex" variable.
     */
    public Integer getJvmRTClassPathIndex() throws SnmpStatusException;

}
