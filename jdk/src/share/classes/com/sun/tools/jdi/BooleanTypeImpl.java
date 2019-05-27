package com.sun.tools.jdi;

import com.sun.jdi.*;

public class BooleanTypeImpl extends PrimitiveTypeImpl implements BooleanType {
    BooleanTypeImpl(VirtualMachine vm) {
        super(vm);
    }

    public String signature() {
        return String.valueOf((char)JDWP.Tag.BOOLEAN);
    }

    PrimitiveValue convert(PrimitiveValue value) throws InvalidTypeException {
        return vm.mirrorOf(((PrimitiveValueImpl)value).checkedBooleanValue());
    }
}
