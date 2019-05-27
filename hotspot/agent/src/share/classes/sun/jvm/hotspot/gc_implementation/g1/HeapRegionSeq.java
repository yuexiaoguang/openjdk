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

// Mirror class for HeapRegionSeq. It essentially encapsulates the G1HeapRegionTable.
public class HeapRegionSeq extends VMObject {
    // G1HeapRegionTable _regions
    static private long regionsFieldOffset;
    // uint _committed_length
    static private CIntegerField committedLengthField;

    static {
        VM.registerVMInitializedObserver(new Observer() {
                public void update(Observable o, Object data) {
                    initialize(VM.getVM().getTypeDataBase());
                }
            });
    }

    static private synchronized void initialize(TypeDataBase db) {
        Type type = db.lookupType("HeapRegionSeq");

        regionsFieldOffset = type.getField("_regions").getOffset();
        committedLengthField = type.getCIntegerField("_committed_length");
    }

    private G1HeapRegionTable regions() {
        Address regionsAddr = addr.addOffsetTo(regionsFieldOffset);
        return (G1HeapRegionTable) VMObjectFactory.newObject(G1HeapRegionTable.class,
                                                             regionsAddr);
    }

    public long length() {
        return regions().length();
    }

    public long committedLength() {
        return committedLengthField.getValue(addr);
    }

    public Iterator<HeapRegion> heapRegionIterator() {
        return regions().heapRegionIterator(committedLength());
    }

    public HeapRegionSeq(Address addr) {
        super(addr);
    }
}
