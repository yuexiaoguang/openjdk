package sun.management.snmp.jvmmib;

// jmx imports
//
import com.sun.jmx.snmp.SnmpStatusException;

/**
 * This interface is used for representing the remote management interface for the "JvmThreading" MBean.
 */
public interface JvmThreadingMBean {

    /**
     * Getter for the "JvmThreadCpuTimeMonitoring" variable.
     */
    public EnumJvmThreadCpuTimeMonitoring getJvmThreadCpuTimeMonitoring() throws SnmpStatusException;

    /**
     * Setter for the "JvmThreadCpuTimeMonitoring" variable.
     */
    public void setJvmThreadCpuTimeMonitoring(EnumJvmThreadCpuTimeMonitoring x) throws SnmpStatusException;

    /**
     * Checker for the "JvmThreadCpuTimeMonitoring" variable.
     */
    public void checkJvmThreadCpuTimeMonitoring(EnumJvmThreadCpuTimeMonitoring x) throws SnmpStatusException;

    /**
     * Getter for the "JvmThreadContentionMonitoring" variable.
     */
    public EnumJvmThreadContentionMonitoring getJvmThreadContentionMonitoring() throws SnmpStatusException;

    /**
     * Setter for the "JvmThreadContentionMonitoring" variable.
     */
    public void setJvmThreadContentionMonitoring(EnumJvmThreadContentionMonitoring x) throws SnmpStatusException;

    /**
     * Checker for the "JvmThreadContentionMonitoring" variable.
     */
    public void checkJvmThreadContentionMonitoring(EnumJvmThreadContentionMonitoring x) throws SnmpStatusException;

    /**
     * Getter for the "JvmThreadTotalStartedCount" variable.
     */
    public Long getJvmThreadTotalStartedCount() throws SnmpStatusException;

    /**
     * Getter for the "JvmThreadPeakCount" variable.
     */
    public Long getJvmThreadPeakCount() throws SnmpStatusException;

    /**
     * Getter for the "JvmThreadDaemonCount" variable.
     */
    public Long getJvmThreadDaemonCount() throws SnmpStatusException;

    /**
     * Getter for the "JvmThreadCount" variable.
     */
    public Long getJvmThreadCount() throws SnmpStatusException;

    /**
     * Getter for the "JvmThreadPeakCountReset" variable.
     */
    public Long getJvmThreadPeakCountReset() throws SnmpStatusException;

    /**
     * Setter for the "JvmThreadPeakCountReset" variable.
     */
    public void setJvmThreadPeakCountReset(Long x) throws SnmpStatusException;

    /**
     * Checker for the "JvmThreadPeakCountReset" variable.
     */
    public void checkJvmThreadPeakCountReset(Long x) throws SnmpStatusException;

}
