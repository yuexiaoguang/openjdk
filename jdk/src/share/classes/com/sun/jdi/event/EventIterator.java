package com.sun.jdi.event;

import com.sun.jdi.*;

import java.util.Iterator;

/**
 * EventIterators are unmodifiable.
 */
@jdk.Exported
public interface EventIterator extends Iterator<Event> {

    /**
     * @return The next {@link Event} in an {@link EventSet}.
     */
    Event nextEvent();
}
