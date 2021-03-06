package com.sun.jdi.event;

import com.sun.jdi.*;

/**
 * Notification of a completed thread in the target VM. The
 * notification is generated by the dying thread before it terminates.
 * Because of this timing, it is possible
 * for {@link VirtualMachine#allThreads} to return this thread
 * after this event is received.
 * <p>
 * Note that this event gives no information
 * about the lifetime of the thread object. It may or may not be collected
 * soon depending on what references exist in the target VM.
 */
@jdk.Exported
public interface ThreadDeathEvent extends Event {
    /**
     * Returns the thread which is terminating.
     *
     * @return a {@link ThreadReference} which mirrors the event's thread in
     * the target VM.
     */
    public ThreadReference thread();
}
