package com.sun.tools.jdi;

import com.sun.jdi.*;

public class ByteTypeImpl extends PrimitiveTypeImpl implements ByteType {
    ByteTypeImpl(VirtualMachine vm) {
        super(vm);
    }


    public String signature() {
        return String.valueOf((char)JDWP.Tag.BYTE);
    }

    PrimitiveValue convert(PrimitiveValue value) throws InvalidTypeException {
        return vm.mirrorOf(((PrimitiveValueImpl)value).checkedByteValue());
    }
}
