package com.sun.tools.jdi;

import com.sun.jdi.*;

public abstract class TypeImpl extends MirrorImpl implements Type
{
    private String myName = null;

    TypeImpl(VirtualMachine vm)
    {
        super(vm);
    }

    public abstract String signature();

    public String name() {
        if (myName == null) {
            JNITypeParser parser = new JNITypeParser(signature());
            myName = parser.typeName();
        }
        return myName;
    }

    public boolean equals(Object obj) {
        if ((obj != null) && (obj instanceof Type)) {
            Type other = (Type)obj;
            return signature().equals(other.signature()) &&
                   super.equals(obj);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return signature().hashCode();
    }
}
