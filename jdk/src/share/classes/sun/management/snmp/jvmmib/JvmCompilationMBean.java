package sun.management.snmp.jvmmib;

// jmx imports
//
import com.sun.jmx.snmp.SnmpStatusException;

/**
 * This interface is used for representing the remote management interface for the "JvmCompilation" MBean.
 */
public interface JvmCompilationMBean {

    /**
     * Getter for the "JvmJITCompilerTimeMonitoring" variable.
     */
    public EnumJvmJITCompilerTimeMonitoring getJvmJITCompilerTimeMonitoring() throws SnmpStatusException;

    /**
     * Getter for the "JvmJITCompilerTimeMs" variable.
     */
    public Long getJvmJITCompilerTimeMs() throws SnmpStatusException;

    /**
     * Getter for the "JvmJITCompilerName" variable.
     */
    public String getJvmJITCompilerName() throws SnmpStatusException;

}
