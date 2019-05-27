package sun.management.snmp.jvmmib;

// jmx imports
//
import com.sun.jmx.snmp.SnmpStatusException;

/**
 * This interface is used for representing the remote management interface for the "JvmMemory" MBean.
 */
public interface JvmMemoryMBean {

    /**
     * Getter for the "JvmMemoryNonHeapMaxSize" variable.
     */
    public Long getJvmMemoryNonHeapMaxSize() throws SnmpStatusException;

    /**
     * Getter for the "JvmMemoryNonHeapCommitted" variable.
     */
    public Long getJvmMemoryNonHeapCommitted() throws SnmpStatusException;

    /**
     * Getter for the "JvmMemoryNonHeapUsed" variable.
     */
    public Long getJvmMemoryNonHeapUsed() throws SnmpStatusException;

    /**
     * Getter for the "JvmMemoryNonHeapInitSize" variable.
     */
    public Long getJvmMemoryNonHeapInitSize() throws SnmpStatusException;

    /**
     * Getter for the "JvmMemoryHeapMaxSize" variable.
     */
    public Long getJvmMemoryHeapMaxSize() throws SnmpStatusException;

    /**
     * Getter for the "JvmMemoryHeapCommitted" variable.
     */
    public Long getJvmMemoryHeapCommitted() throws SnmpStatusException;

    /**
     * Getter for the "JvmMemoryGCCall" variable.
     */
    public EnumJvmMemoryGCCall getJvmMemoryGCCall() throws SnmpStatusException;

    /**
     * Setter for the "JvmMemoryGCCall" variable.
     */
    public void setJvmMemoryGCCall(EnumJvmMemoryGCCall x) throws SnmpStatusException;

    /**
     * Checker for the "JvmMemoryGCCall" variable.
     */
    public void checkJvmMemoryGCCall(EnumJvmMemoryGCCall x) throws SnmpStatusException;

    /**
     * Getter for the "JvmMemoryHeapUsed" variable.
     */
    public Long getJvmMemoryHeapUsed() throws SnmpStatusException;

    /**
     * Getter for the "JvmMemoryGCVerboseLevel" variable.
     */
    public EnumJvmMemoryGCVerboseLevel getJvmMemoryGCVerboseLevel() throws SnmpStatusException;

    /**
     * Setter for the "JvmMemoryGCVerboseLevel" variable.
     */
    public void setJvmMemoryGCVerboseLevel(EnumJvmMemoryGCVerboseLevel x) throws SnmpStatusException;

    /**
     * Checker for the "JvmMemoryGCVerboseLevel" variable.
     */
    public void checkJvmMemoryGCVerboseLevel(EnumJvmMemoryGCVerboseLevel x) throws SnmpStatusException;

    /**
     * Getter for the "JvmMemoryHeapInitSize" variable.
     */
    public Long getJvmMemoryHeapInitSize() throws SnmpStatusException;

    /**
     * Getter for the "JvmMemoryPendingFinalCount" variable.
     */
    public Long getJvmMemoryPendingFinalCount() throws SnmpStatusException;

}
