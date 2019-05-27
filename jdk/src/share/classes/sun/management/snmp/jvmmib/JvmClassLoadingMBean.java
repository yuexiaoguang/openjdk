package sun.management.snmp.jvmmib;

// jmx imports
//
import com.sun.jmx.snmp.SnmpStatusException;

/**
 * This interface is used for representing the remote management interface for the "JvmClassLoading" MBean.
 */
public interface JvmClassLoadingMBean {

    /**
     * Getter for the "JvmClassesVerboseLevel" variable.
     */
    public EnumJvmClassesVerboseLevel getJvmClassesVerboseLevel() throws SnmpStatusException;

    /**
     * Setter for the "JvmClassesVerboseLevel" variable.
     */
    public void setJvmClassesVerboseLevel(EnumJvmClassesVerboseLevel x) throws SnmpStatusException;

    /**
     * Checker for the "JvmClassesVerboseLevel" variable.
     */
    public void checkJvmClassesVerboseLevel(EnumJvmClassesVerboseLevel x) throws SnmpStatusException;

    /**
     * Getter for the "JvmClassesUnloadedCount" variable.
     */
    public Long getJvmClassesUnloadedCount() throws SnmpStatusException;

    /**
     * Getter for the "JvmClassesTotalLoadedCount" variable.
     */
    public Long getJvmClassesTotalLoadedCount() throws SnmpStatusException;

    /**
     * Getter for the "JvmClassesLoadedCount" variable.
     */
    public Long getJvmClassesLoadedCount() throws SnmpStatusException;

}
