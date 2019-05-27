package sun.management.snmp.jvmmib;

// jmx imports
//
import com.sun.jmx.snmp.SnmpStatusException;

/**
 * This interface is used for representing the remote management interface for the "JvmOS" MBean.
 */
public interface JvmOSMBean {

    /**
     * Getter for the "JvmOSProcessorCount" variable.
     */
    public Integer getJvmOSProcessorCount() throws SnmpStatusException;

    /**
     * Getter for the "JvmOSVersion" variable.
     */
    public String getJvmOSVersion() throws SnmpStatusException;

    /**
     * Getter for the "JvmOSArch" variable.
     */
    public String getJvmOSArch() throws SnmpStatusException;

    /**
     * Getter for the "JvmOSName" variable.
     */
    public String getJvmOSName() throws SnmpStatusException;

}
