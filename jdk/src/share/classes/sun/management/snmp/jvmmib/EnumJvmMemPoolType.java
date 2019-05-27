package sun.management.snmp.jvmmib;

// java imports
//
import java.io.Serializable;
import java.util.Hashtable;

// RI imports
//
import com.sun.jmx.snmp.Enumerated;

/**
 * The class is used for representing "JvmMemPoolType".
 */
public class EnumJvmMemPoolType extends Enumerated implements Serializable {

    static final long serialVersionUID = -7214498472962396555L;
    protected static Hashtable<Integer, String> intTable =
            new Hashtable<>();
    protected static Hashtable<String, Integer> stringTable =
            new Hashtable<>();
    static  {
        intTable.put(new Integer(2), "heap");
        intTable.put(new Integer(1), "nonheap");
        stringTable.put("heap", new Integer(2));
        stringTable.put("nonheap", new Integer(1));
    }

    public EnumJvmMemPoolType(int valueIndex) throws IllegalArgumentException {
        super(valueIndex);
    }

    public EnumJvmMemPoolType(Integer valueIndex) throws IllegalArgumentException {
        super(valueIndex);
    }

    public EnumJvmMemPoolType() throws IllegalArgumentException {
        super();
    }

    public EnumJvmMemPoolType(String x) throws IllegalArgumentException {
        super(x);
    }

    protected Hashtable<Integer,String> getIntTable() {
        return intTable ;
    }

    protected Hashtable<String,Integer> getStringTable() {
        return stringTable ;
    }

}
