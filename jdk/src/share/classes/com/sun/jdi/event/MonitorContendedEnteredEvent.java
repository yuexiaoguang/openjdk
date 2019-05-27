package com.sun.jdi.event;

import com.sun.jdi.*;

/**
 *  Notification that a thread in the target VM is entering a monitor
 *  after waiting for it to be released by another thread.
 * <P>
 */
@jdk.Exported
public interface MonitorContendedEnteredEvent extends LocatableEvent {

    /**
     * Returns the thread in which this event has occurred.
     * <p>
     *
     * @return a {@link ThreadReference} which mirrors the event's thread in
     * the target VM.
     */
    public ThreadReference thread();

    /**
     * Returns the monitor that was entered.
     *
     * @return an {@link ObjectReference} for the monitor.
     */
    public ObjectReference  monitor();

}
