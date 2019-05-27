package com.sun.jdi.request;

import com.sun.jdi.*;

/**
 * Request for notification when a thread starts execution in the target VM.
 * When an enabled ThreadStartRequest is hit, an
 * {@link com.sun.jdi.event.EventSet event set} containing a
 * {@link com.sun.jdi.event.ThreadStartEvent ThreadStartEvent}
 * will be placed on the
 * {@link com.sun.jdi.event.EventQueue EventQueue}.
 * The collection of existing ThreadStartRequests is
 * managed by the {@link EventRequestManager}
 */
@jdk.Exported
public interface ThreadStartRequest extends EventRequest {

    /**
     * Restricts the events generated by this request to those in
     * the given thread.
     * @param thread the thread to filter on.
     * @throws InvalidRequestStateException if this request is currently
     * enabled or has been deleted.
     * Filters may be added only to disabled requests.
     */
    void addThreadFilter(ThreadReference thread);
}
