package sun.management.snmp.jvmmib;

// jmx imports
//
import com.sun.jmx.snmp.SnmpStatusException;

/**
 * This interface is used for representing the remote management interface for the "JvmThreadInstanceEntry" MBean.
 */
public interface JvmThreadInstanceEntryMBean {

    /**
     * Getter for the "JvmThreadInstName" variable.
     */
    public String getJvmThreadInstName() throws SnmpStatusException;

    /**
     * Getter for the "JvmThreadInstCpuTimeNs" variable.
     */
    public Long getJvmThreadInstCpuTimeNs() throws SnmpStatusException;

    /**
     * Getter for the "JvmThreadInstWaitTimeMs" variable.
     */
    public Long getJvmThreadInstWaitTimeMs() throws SnmpStatusException;

    /**
     * Getter for the "JvmThreadInstWaitCount" variable.
     */
    public Long getJvmThreadInstWaitCount() throws SnmpStatusException;

    /**
     * Getter for the "JvmThreadInstBlockTimeMs" variable.
     */
    public Long getJvmThreadInstBlockTimeMs() throws SnmpStatusException;

    /**
     * Getter for the "JvmThreadInstBlockCount" variable.
     */
    public Long getJvmThreadInstBlockCount() throws SnmpStatusException;

    /**
     * Getter for the "JvmThreadInstState" variable.
     */
    public Byte[] getJvmThreadInstState() throws SnmpStatusException;

    /**
     * Getter for the "JvmThreadInstLockOwnerPtr" variable.
     */
    public String getJvmThreadInstLockOwnerPtr() throws SnmpStatusException;

    /**
     * Getter for the "JvmThreadInstId" variable.
     */
    public Long getJvmThreadInstId() throws SnmpStatusException;

    /**
     * Getter for the "JvmThreadInstLockName" variable.
     */
    public String getJvmThreadInstLockName() throws SnmpStatusException;

    /**
     * Getter for the "JvmThreadInstIndex" variable.
     */
    public Byte[] getJvmThreadInstIndex() throws SnmpStatusException;

}
