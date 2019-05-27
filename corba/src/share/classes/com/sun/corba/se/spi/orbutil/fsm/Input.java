package com.sun.corba.se.spi.orbutil.fsm;

/**
 * This interface must be implemented by any class that is used as
 * an input to a FSM.  The FSM only needs the identity of this
 * object, so all that is really needs is the default equals implementation.
 * The toString() method should also be overridden to give a concise
 * description or name of the input.
 */
public interface Input
{
}
