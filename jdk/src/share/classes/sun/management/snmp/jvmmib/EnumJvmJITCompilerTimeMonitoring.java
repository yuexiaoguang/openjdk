package sun.management.snmp.jvmmib;

// java imports
//
import java.io.Serializable;
import java.util.Hashtable;

// RI imports
//
import com.sun.jmx.snmp.Enumerated;

/**
 * The class is used for representing "JvmJITCompilerTimeMonitoring".
 */
public class EnumJvmJITCompilerTimeMonitoring extends Enumerated implements Serializable {

    static final long serialVersionUID = 3953565918146461236L;
    protected static Hashtable<Integer, String> intTable =
            new Hashtable<>();
    protected static Hashtable<String, Integer> stringTable =
            new Hashtable<>();
    static  {
        intTable.put(new Integer(2), "supported");
        intTable.put(new Integer(1), "unsupported");
        stringTable.put("supported", new Integer(2));
        stringTable.put("unsupported", new Integer(1));
    }

    public EnumJvmJITCompilerTimeMonitoring(int valueIndex) throws IllegalArgumentException {
        super(valueIndex);
    }

    public EnumJvmJITCompilerTimeMonitoring(Integer valueIndex) throws IllegalArgumentException {
        super(valueIndex);
    }

    public EnumJvmJITCompilerTimeMonitoring() throws IllegalArgumentException {
        super();
    }

    public EnumJvmJITCompilerTimeMonitoring(String x) throws IllegalArgumentException {
        super(x);
    }

    protected Hashtable<Integer, String> getIntTable() {
        return intTable ;
    }

    protected Hashtable<String, Integer> getStringTable() {
        return stringTable ;
    }

}
