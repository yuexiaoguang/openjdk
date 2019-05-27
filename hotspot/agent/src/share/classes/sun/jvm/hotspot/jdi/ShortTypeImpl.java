package sun.jvm.hotspot.jdi;

import com.sun.jdi.*;

public class ShortTypeImpl extends PrimitiveTypeImpl implements ShortType {
    ShortTypeImpl(VirtualMachine vm) {
        super(vm);
    }


    public String signature() {
        return "S";
    }

    PrimitiveValue convert(PrimitiveValue value) throws InvalidTypeException {
        return vm.mirrorOf(((PrimitiveValueImpl)value).checkedShortValue());
    }

}
