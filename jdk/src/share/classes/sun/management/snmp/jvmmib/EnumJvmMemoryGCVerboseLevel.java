package sun.management.snmp.jvmmib;

// java imports
//
import java.io.Serializable;
import java.util.Hashtable;

// RI imports
//
import com.sun.jmx.snmp.Enumerated;

/**
 * The class is used for representing "JvmMemoryGCVerboseLevel".
 */
public class EnumJvmMemoryGCVerboseLevel extends Enumerated implements Serializable {

    static final long serialVersionUID = 1362427628755978190L;
    protected static Hashtable<Integer, String> intTable =
            new Hashtable<>();
    protected static Hashtable<String, Integer> stringTable =
            new Hashtable<>();
    static  {
        intTable.put(new Integer(2), "verbose");
        intTable.put(new Integer(1), "silent");
        stringTable.put("verbose", new Integer(2));
        stringTable.put("silent", new Integer(1));
    }

    public EnumJvmMemoryGCVerboseLevel(int valueIndex) throws IllegalArgumentException {
        super(valueIndex);
    }

    public EnumJvmMemoryGCVerboseLevel(Integer valueIndex) throws IllegalArgumentException {
        super(valueIndex);
    }

    public EnumJvmMemoryGCVerboseLevel() throws IllegalArgumentException {
        super();
    }

    public EnumJvmMemoryGCVerboseLevel(String x) throws IllegalArgumentException {
        super(x);
    }

    protected Hashtable<Integer,String> getIntTable() {
        return intTable ;
    }

    protected Hashtable<String,Integer> getStringTable() {
        return stringTable ;
    }

}
