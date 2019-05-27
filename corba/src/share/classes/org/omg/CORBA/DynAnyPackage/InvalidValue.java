package org.omg.CORBA.DynAnyPackage;

/**
 * Dynamic Any insert operations raise the <code>InvalidValue</code>
 * exception if the value inserted is not consistent with the type
 * of the accessed component in the <code>DynAny</code> object.
 */
public final class InvalidValue
    extends org.omg.CORBA.UserException {

    /**
     * Constructs an <code>InvalidValue</code> object.
     */
    public InvalidValue() {
        super();
    }

    /**
     * Constructs an <code>InvalidValue</code> object.
     * @param reason  a <code>String</code> giving more information
     * regarding the exception.
     */
    public InvalidValue(String reason) {
        super(reason);
    }
}
