package com.sun.corba.se.impl.encoding;

/**
 * Defines the methods on an input stream which provide
 * a way to get and restore its internal state without
 * violating encapsulation.
 */
interface RestorableInputStream
{
    Object createStreamMemento();

    void restoreInternalState(Object streamMemento);
}
