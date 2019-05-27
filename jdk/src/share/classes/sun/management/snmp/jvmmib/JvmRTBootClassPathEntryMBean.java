package sun.management.snmp.jvmmib;

// jmx imports
//
import com.sun.jmx.snmp.SnmpStatusException;

/**
 * This interface is used for representing the remote management interface for the "JvmRTBootClassPathEntry" MBean.
 */
public interface JvmRTBootClassPathEntryMBean {

    /**
     * Getter for the "JvmRTBootClassPathItem" variable.
     */
    public String getJvmRTBootClassPathItem() throws SnmpStatusException;

    /**
     * Getter for the "JvmRTBootClassPathIndex" variable.
     */
    public Integer getJvmRTBootClassPathIndex() throws SnmpStatusException;

}
