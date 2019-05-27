package com.sun.jdi.event;

import com.sun.jdi.*;

/**
 * Notification of disconnection from target VM.
 * May be caused by normal termination of a VM,
 * VM termination by uncaught exception or other error,
 * debugger action (
 * {@link VirtualMachine#dispose} or
 * {@link VirtualMachine#exit}) or by external events
 * (for example, target process termination by the
 * operating system, transport termination, etc).
 * <p>
 * If the target VM terminates before the disconnection, this event
 * will be preceded by a {@link VMDeathEvent}.
 * <p>
 * This event is always sent.
 * There is no corresponding {@link com.sun.jdi.request.EventRequest}.
 * The enclosing singleton {@link EventSet} always has a
 * suspend policy of {@link com.sun.jdi.request.EventRequest#SUSPEND_NONE}.
 */
@jdk.Exported
public interface VMDisconnectEvent extends Event {
}
