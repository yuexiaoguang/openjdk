package sun.management.snmp.jvminstr;

// java imports
//
import java.io.Serializable;

// jmx imports
//
import com.sun.jmx.snmp.SnmpStatusException;

// jdmk imports
//
import com.sun.jmx.snmp.agent.SnmpMib;

import sun.management.snmp.jvmmib.JvmRTLibraryPathEntryMBean;

/**
 * The class is used for implementing the "JvmRTLibraryPathEntry" group.
 */
public class JvmRTLibraryPathEntryImpl implements JvmRTLibraryPathEntryMBean,
                                                Serializable {

    static final long serialVersionUID = -3322438153507369765L;
    private final String item;
    private final int index;

    /**
     * Constructor for the "JvmRTLibraryPathEntry" group.
     */
    public JvmRTLibraryPathEntryImpl(String item, int index) {
        this.item  = validPathElementTC(item);
        this.index = index;
    }

    private String validPathElementTC(String str) {
        return JVM_MANAGEMENT_MIB_IMPL.validPathElementTC(str);
    }

    /**
     * Getter for the "JvmRTLibraryPathItem" variable.
     */
    public String getJvmRTLibraryPathItem() throws SnmpStatusException {
        return item;
    }

    /**
     * Getter for the "JvmRTLibraryPathIndex" variable.
     */
    public Integer getJvmRTLibraryPathIndex() throws SnmpStatusException {
        return new Integer(index);
    }

}
