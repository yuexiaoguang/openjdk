package sun.jvm.hotspot.gc_implementation.g1;

import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import sun.jvm.hotspot.debugger.Address;
import sun.jvm.hotspot.runtime.VM;
import sun.jvm.hotspot.runtime.VMObject;
import sun.jvm.hotspot.runtime.VMObjectFactory;
import sun.jvm.hotspot.types.AddressField;
import sun.jvm.hotspot.types.CIntegerField;
import sun.jvm.hotspot.types.Type;
import sun.jvm.hotspot.types.TypeDataBase;

// Mirror class for HeapRegionSetBase. Represents a group of regions.
public class HeapRegionSetBase extends VMObject {
    // uint _length;
    static private CIntegerField lengthField;
    // uint _region_num;
    static private CIntegerField regionNumField;
    // size_t _total_used_bytes;
    static private CIntegerField totalUsedBytesField;

    static {
        VM.registerVMInitializedObserver(new Observer() {
                public void update(Observable o, Object data) {
                    initialize(VM.getVM().getTypeDataBase());
                }
            });
    }

    static private synchronized void initialize(TypeDataBase db) {
        Type type = db.lookupType("HeapRegionSetBase");

        lengthField         = type.getCIntegerField("_length");
        regionNumField      = type.getCIntegerField("_region_num");
        totalUsedBytesField = type.getCIntegerField("_total_used_bytes");
    }

    public long length() {
        return lengthField.getValue(addr);
    }

    public long regionNum() {
        return regionNumField.getValue(addr);
    }

    public long totalUsedBytes() {
        return totalUsedBytesField.getValue(addr);
    }

    public HeapRegionSetBase(Address addr) {
        super(addr);
    }
}
