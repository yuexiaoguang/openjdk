package sun.management.snmp.jvmmib;

// java imports
//
import java.io.Serializable;
import java.util.Hashtable;

// RI imports
//
import com.sun.jmx.snmp.Enumerated;

/**
 * The class is used for representing "JvmMemPoolState".
 */
public class EnumJvmMemPoolState extends Enumerated implements Serializable {

    static final long serialVersionUID = 3038175407527743027L;
    protected static Hashtable<Integer, String> intTable =
            new Hashtable<>();
    protected static Hashtable<String, Integer> stringTable =
            new Hashtable<>();
    static  {
        intTable.put(new Integer(2), "valid");
        intTable.put(new Integer(1), "invalid");
        stringTable.put("valid", new Integer(2));
        stringTable.put("invalid", new Integer(1));
    }

    public EnumJvmMemPoolState(int valueIndex) throws IllegalArgumentException {
        super(valueIndex);
    }

    public EnumJvmMemPoolState(Integer valueIndex) throws IllegalArgumentException {
        super(valueIndex);
    }

    public EnumJvmMemPoolState() throws IllegalArgumentException {
        super();
    }

    public EnumJvmMemPoolState(String x) throws IllegalArgumentException {
        super(x);
    }

    protected Hashtable<Integer,String> getIntTable() {
        return intTable ;
    }

    protected Hashtable<String,Integer> getStringTable() {
        return stringTable ;
    }

}
