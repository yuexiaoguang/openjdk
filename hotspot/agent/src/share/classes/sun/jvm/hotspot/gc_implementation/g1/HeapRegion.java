package sun.jvm.hotspot.gc_implementation.g1;

import java.util.Observable;
import java.util.Observer;

import sun.jvm.hotspot.debugger.Address;
import sun.jvm.hotspot.memory.ContiguousSpace;
import sun.jvm.hotspot.runtime.VM;
import sun.jvm.hotspot.types.CIntegerField;
import sun.jvm.hotspot.types.Type;
import sun.jvm.hotspot.types.TypeDataBase;

// Mirror class for HeapRegion. Currently we don't actually include
// any of its fields but only iterate over it (which we get "for free"
// as HeapRegion ultimately inherits from ContiguousSpace).
public class HeapRegion extends ContiguousSpace {
    // static int GrainBytes;
    static private CIntegerField grainBytesField;

    static {
        VM.registerVMInitializedObserver(new Observer() {
                public void update(Observable o, Object data) {
                    initialize(VM.getVM().getTypeDataBase());
                }
            });
    }

    static private synchronized void initialize(TypeDataBase db) {
        Type type = db.lookupType("HeapRegion");

        grainBytesField = type.getCIntegerField("GrainBytes");
    }

    static public long grainBytes() {
        return grainBytesField.getValue();
    }

    public HeapRegion(Address addr) {
        super(addr);
    }
}
