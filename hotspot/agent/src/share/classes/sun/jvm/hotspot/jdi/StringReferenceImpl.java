package sun.jvm.hotspot.jdi;

import com.sun.jdi.*;
import sun.jvm.hotspot.oops.Instance;
import sun.jvm.hotspot.oops.OopUtilities;

public class StringReferenceImpl extends ObjectReferenceImpl
    implements StringReference
{
    private String value;

    StringReferenceImpl(VirtualMachine aVm, sun.jvm.hotspot.oops.Instance oRef) {
        super(aVm,oRef);
        value = OopUtilities.stringOopToString(oRef);
    }

    public String value() {
        return value;
    }

    public String toString() {
        return "\"" + value() + "\"";
    }
}
