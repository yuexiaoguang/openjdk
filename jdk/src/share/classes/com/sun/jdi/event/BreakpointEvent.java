package com.sun.jdi.event;

import com.sun.jdi.*;

import java.util.List;

/**
 * Notification of a breakpoint in the target VM.
 * The breakpoint event
 * is generated before the code at its location is executed.
 * When a location
 * is reached which satisfies a currently enabled
 * {@link com.sun.jdi.request.BreakpointRequest breakpoint request},
 * an {@link EventSet event set}
 * containing an instance of this class will be added
 * to the VM's event queue.
 */
@jdk.Exported
public interface BreakpointEvent extends LocatableEvent {
}
