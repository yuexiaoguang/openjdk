package sun.management.snmp.jvmmib;

// java imports
//
import java.io.Serializable;
import java.util.Hashtable;

// RI imports
//
import com.sun.jmx.snmp.Enumerated;

/**
 * The class is used for representing "JvmClassesVerboseLevel".
 */
public class EnumJvmClassesVerboseLevel extends Enumerated implements Serializable {

    static final long serialVersionUID = -620710366914810374L;
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

    public EnumJvmClassesVerboseLevel(int valueIndex) throws IllegalArgumentException {
        super(valueIndex);
    }

    public EnumJvmClassesVerboseLevel(Integer valueIndex) throws IllegalArgumentException {
        super(valueIndex);
    }

    public EnumJvmClassesVerboseLevel() throws IllegalArgumentException {
        super();
    }

    public EnumJvmClassesVerboseLevel(String x) throws IllegalArgumentException {
        super(x);
    }

    protected Hashtable<Integer,String> getIntTable() {
        return intTable ;
    }

    protected Hashtable<String,Integer> getStringTable() {
        return stringTable ;
    }

}
