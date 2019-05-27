package sun.jvm.hotspot.jdi;

import com.sun.jdi.*;

abstract class ValueImpl extends MirrorImpl implements Value {
    ValueImpl(VirtualMachine aVm) {
        super(aVm);
    }

    // type() is in the subclasses
}
