package com.sun.jdi.event;

import com.sun.jdi.*;

/**
 * Notification that a thread in the target VM is about to
 * wait on a monitor object.
 * <P>
 */
@jdk.Exported
public interface MonitorWaitEvent extends LocatableEvent {

    /**
     * Returns the thread in which monitor wait event has occurred.
     * <p>
     *
     * @return a {@link ThreadReference} which mirrors the event's thread in
     * the target VM.
     */
    public ThreadReference thread();

    /**
     * Returns the monitor object that the thread about to wait.
     *
     * @return an {@link ObjectReference} for the monitor.
     */
    public ObjectReference  monitor();

    /**
     * Returns the number of millisecond the thread will wait.
     *
     * @return a <code>jlong</code> containing monitor wait time in milliseconds.
     */
    public long  timeout();
}
