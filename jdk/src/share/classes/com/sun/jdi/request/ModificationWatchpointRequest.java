package com.sun.jdi.request;

import com.sun.jdi.*;

/**
 * Request for notification when a field is set.
 * This event will be triggered when a value is assigned to the specified
 * field with a Java<SUP><FONT SIZE="-2">TM</FONT></SUP> programming
 * language statement (assignment, increment, etc) or by a
 * Java Native Interface (JNI) set function (<code>Set&lt;Type&gt;Field,
 * SetStatic&lt;Type&gt;Field</code>).
 * Setting a field to a value which is the same as the previous value
 * still triggers this event.
 * Modification by JDI does not trigger this event.
 * When an enabled
 * ModificationWatchpointRequest is satisfied, an
 * {@link com.sun.jdi.event.EventSet event set} containing a
 * {@link com.sun.jdi.event.ModificationWatchpointEvent ModificationWatchpointEvent}
 * will be placed on
 * the {@link com.sun.jdi.event.EventQueue EventQueue}.
 * The collection of existing
 * watchpoints is
 * managed by the {@link EventRequestManager}.
 */
@jdk.Exported
public interface ModificationWatchpointRequest extends WatchpointRequest {
}
