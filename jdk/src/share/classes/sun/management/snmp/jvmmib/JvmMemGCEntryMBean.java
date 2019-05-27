package sun.management.snmp.jvmmib;

// jmx imports
//
import com.sun.jmx.snmp.SnmpStatusException;

/**
 * This interface is used for representing the remote management interface for the "JvmMemGCEntry" MBean.
 */
public interface JvmMemGCEntryMBean {

    /**
     * Getter for the "JvmMemGCTimeMs" variable.
     */
    public Long getJvmMemGCTimeMs() throws SnmpStatusException;

    /**
     * Getter for the "JvmMemGCCount" variable.
     */
    public Long getJvmMemGCCount() throws SnmpStatusException;

    /**
     * Getter for the "JvmMemManagerIndex" variable.
     */
    public Integer getJvmMemManagerIndex() throws SnmpStatusException;

}
