package sun.management.snmp.jvmmib;

// jmx imports
//
import com.sun.jmx.snmp.SnmpStatusException;

/**
 * This interface is used for representing the remote management interface for the "JvmMemMgrPoolRelEntry" MBean.
 */
public interface JvmMemMgrPoolRelEntryMBean {

    /**
     * Getter for the "JvmMemMgrRelPoolName" variable.
     */
    public String getJvmMemMgrRelPoolName() throws SnmpStatusException;

    /**
     * Getter for the "JvmMemMgrRelManagerName" variable.
     */
    public String getJvmMemMgrRelManagerName() throws SnmpStatusException;

    /**
     * Getter for the "JvmMemManagerIndex" variable.
     */
    public Integer getJvmMemManagerIndex() throws SnmpStatusException;

    /**
     * Getter for the "JvmMemPoolIndex" variable.
     */
    public Integer getJvmMemPoolIndex() throws SnmpStatusException;

}
