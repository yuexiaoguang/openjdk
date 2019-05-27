package com.sun.corba.se.spi.orbutil.fsm;

import com.sun.corba.se.impl.orbutil.fsm.StateEngineImpl ;

/**
 * Factory for creating the standard state machine implementation.
 */
public class StateEngineFactory {
    private StateEngineFactory() {}

    public static StateEngine create()
    {
        return new StateEngineImpl() ;
    }
}
