package com.sun.tools.jdi;

import com.sun.jdi.*;

public class IntegerTypeImpl extends PrimitiveTypeImpl implements IntegerType {
    IntegerTypeImpl(VirtualMachine vm) {
        super(vm);
    }


    public String signature() {
        return String.valueOf((char)JDWP.Tag.INT);
    }

    PrimitiveValue convert(PrimitiveValue value) throws InvalidTypeException {
        return vm.mirrorOf(((PrimitiveValueImpl)value).checkedIntValue());
    }

}
