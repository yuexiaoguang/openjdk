package com.sun.jdi.request;

import com.sun.jdi.*;

/**
 * Request for notification when the contents of a field are accessed
 * in the target VM.
 * This event will be triggered when the specified field is accessed
 * by Java<SUP><FONT SIZE="-2">TM</FONT></SUP> programming
 * language code or by a
 * Java Native Interface (JNI) get function (<code>Get&lt;Type&gt;Field,
 * GetStatic&lt;Type&gt;Field</code>).
 * Access by JDI does not trigger this event.
 * When an enabled AccessWatchpointRequest is satisfied, an
 * {@link com.sun.jdi.event.EventSet event set} containing an
 * {@link com.sun.jdi.event.AccessWatchpointEvent AccessWatchpointEvent} will be placed
 * on the {@link com.sun.jdi.event.EventQueue EventQueue}.
 * The collection of existing ExceptionRequests is
 * managed by the {@link EventRequestManager}
 * The collection of existing
 * watchpoints is
 * managed by the {@link EventRequestManager}.
 * <p>
 * Note that the modification
 * of a Field is not considered an access.
 */
@jdk.Exported
public interface AccessWatchpointRequest extends WatchpointRequest {
}
