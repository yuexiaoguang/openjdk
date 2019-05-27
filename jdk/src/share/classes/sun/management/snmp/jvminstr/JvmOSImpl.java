package sun.management.snmp.jvminstr;

// java imports
//
import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

// jmx imports
//
import javax.management.MBeanServer;
import com.sun.jmx.snmp.SnmpString;
import com.sun.jmx.snmp.SnmpStatusException;

// jdmk imports
//
import com.sun.jmx.snmp.agent.SnmpMib;

import sun.management.snmp.jvmmib.JvmOSMBean;

/**
 * The class is used for implementing the "JvmOS" group.
 */
public class JvmOSImpl implements JvmOSMBean, Serializable {

     static final long serialVersionUID = 1839834731763310809L;

    /**
     * Constructor for the "JvmOS" group.
     * If the group contains a table, the entries created through an
     * SNMP SET will not be registered in Java DMK.
     */
    public JvmOSImpl(SnmpMib myMib) {
    }


    /**
     * Constructor for the "JvmOS" group.
     * If the group contains a table, the entries created through an
     * SNMP SET will be AUTOMATICALLY REGISTERED in Java DMK.
     */
    public JvmOSImpl(SnmpMib myMib, MBeanServer server) {
    }

    static OperatingSystemMXBean getOSMBean() {
        return ManagementFactory.getOperatingSystemMXBean();
    }

    private static String validDisplayStringTC(String str) {
        return JVM_MANAGEMENT_MIB_IMPL.validDisplayStringTC(str);
    }

    private static String validJavaObjectNameTC(String str) {
        return JVM_MANAGEMENT_MIB_IMPL.validJavaObjectNameTC(str);
    }

    /**
     * Getter for the "JvmRTProcessorCount" variable.
     */
    public Integer getJvmOSProcessorCount() throws SnmpStatusException {
        return new Integer(getOSMBean().getAvailableProcessors());

    }

    /**
     * Getter for the "JvmOSVersion" variable.
     */
    public String getJvmOSVersion() throws SnmpStatusException {
        return validDisplayStringTC(getOSMBean().getVersion());
    }

    /**
     * Getter for the "JvmOSArch" variable.
     */
    public String getJvmOSArch() throws SnmpStatusException {
        return validDisplayStringTC(getOSMBean().getArch());
    }

    /**
     * Getter for the "JvmOSName" variable.
     */
    public String getJvmOSName() throws SnmpStatusException {
        return validJavaObjectNameTC(getOSMBean().getName());
    }

}
