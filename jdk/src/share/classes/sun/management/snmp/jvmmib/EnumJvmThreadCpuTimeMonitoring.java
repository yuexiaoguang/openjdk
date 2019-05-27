package sun.management.snmp.jvmmib;

// java imports
//
import java.io.Serializable;
import java.util.Hashtable;

// RI imports
//
import com.sun.jmx.snmp.Enumerated;

/**
 * The class is used for representing "JvmThreadCpuTimeMonitoring".
 */
public class EnumJvmThreadCpuTimeMonitoring extends Enumerated implements Serializable {

    static final long serialVersionUID = -532837824105215699L;
    protected static Hashtable<Integer, String> intTable =
            new Hashtable<>();
    protected static Hashtable<String, Integer> stringTable =
            new Hashtable<>();
    static  {
        intTable.put(new Integer(3), "enabled");
        intTable.put(new Integer(4), "disabled");
        intTable.put(new Integer(1), "unsupported");
        stringTable.put("enabled", new Integer(3));
        stringTable.put("disabled", new Integer(4));
        stringTable.put("unsupported", new Integer(1));
    }

    public EnumJvmThreadCpuTimeMonitoring(int valueIndex) throws IllegalArgumentException {
        super(valueIndex);
    }

    public EnumJvmThreadCpuTimeMonitoring(Integer valueIndex) throws IllegalArgumentException {
        super(valueIndex);
    }

    public EnumJvmThreadCpuTimeMonitoring() throws IllegalArgumentException {
        super();
    }

    public EnumJvmThreadCpuTimeMonitoring(String x) throws IllegalArgumentException {
        super(x);
    }

    protected Hashtable<Integer,String> getIntTable() {
        return intTable ;
    }

    protected Hashtable<String,Integer> getStringTable() {
        return stringTable ;
    }

}
