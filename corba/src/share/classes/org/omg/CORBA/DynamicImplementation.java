package org.omg.CORBA;

import org.omg.CORBA.portable.ObjectImpl;

/**
 * @deprecated org.omg.CORBA.DynamicImplementation
 */
@Deprecated
public class DynamicImplementation extends org.omg.CORBA.portable.ObjectImpl {

    /**
      * @deprecated Deprecated by Portable Object Adapter
      */
    @Deprecated
    public void invoke(ServerRequest request) {
        throw new org.omg.CORBA.NO_IMPLEMENT();
    }

    public String[] _ids() {
        throw new org.omg.CORBA.NO_IMPLEMENT();
    }
}
