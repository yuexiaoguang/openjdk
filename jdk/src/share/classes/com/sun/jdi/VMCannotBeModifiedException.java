package com.sun.jdi;

/**
 * Thrown to indicate that the operation is invalid because it would
 * modify the VM and the VM is read-only.  See {@link VirtualMachine#canBeModified()}.
 */
@jdk.Exported
public class VMCannotBeModifiedException extends UnsupportedOperationException {
    private static final long serialVersionUID = -4063879815130164009L;
    public VMCannotBeModifiedException() {
        super();
    }

    public VMCannotBeModifiedException(String s) {
        super(s);
    }
}
