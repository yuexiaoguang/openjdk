package com.sun.jdi.event;

import com.sun.jdi.*;

/**
 *
 *  Notification that a thread in the target VM is attempting
 *  to enter a monitor that is already acquired by another thread.
 * <P>
 */
@jdk.Exported
public interface MonitorContendedEnterEvent extends LocatableEvent {

    /**
     * Returns the thread in which this event has occurred.
     * <p>
     *
     * @return a {@link ThreadReference} which mirrors the event's thread in
     * the target VM.
     */
    public ThreadReference thread();

    /**
     * Returns the method that was entered.
     *
     * @return an {@link ObjectReference} for the monitor.
     */
    public ObjectReference  monitor();
}
